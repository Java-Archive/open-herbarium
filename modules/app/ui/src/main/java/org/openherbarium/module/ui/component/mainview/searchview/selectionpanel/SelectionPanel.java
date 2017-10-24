package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist.SelectionListSubscriber;
import org.openherbarium.module.ui.component.mainview.searchview.interfaces.selectionlist.VaadinSelectionListSubject;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.MetadataPanel;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.TaskPanel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.openherbarium.module.ui.component.mainview.searchview.SearchView.MAX_SELECTED_METADATA;

public class SelectionPanel extends Panel
    implements SelectionListSubscriber, VaadinSelectionListSubject<Metadata> {

  private static final String SELECTION = "Auswahl";

  private VerticalLayout contentLayout = new VerticalLayout();
  private TaskPanel taskPanel = new TaskPanel();
  private Panel metadataPanelContainer = new Panel();
  private VerticalLayout metadataPanelLayout = new VerticalLayout();

  private Map<Metadata, MetadataPanel> metadataPanelByMetadata = new LinkedHashMap<>();

  private List<SelectionListSubscriber> subscribers = new ArrayList<>();

  public SelectionPanel() {
    setCaption(SELECTION);
    contentLayout.addComponents(taskPanel, metadataPanelContainer);
    doLayout();
    setContent(contentLayout);
    rebuildMetaDataPanels();
  }

  private void doLayout() {
    setSizeFull();
    contentLayout.setSizeFull();
    contentLayout.setMargin(false);
    contentLayout.setSpacing(false);
    taskPanel.setWidth(100, Unit.PERCENTAGE);
    metadataPanelContainer.setSizeFull();
    metadataPanelLayout.setWidth(100, Unit.PERCENTAGE);
    metadataPanelContainer.setContent(metadataPanelLayout);
    contentLayout.setExpandRatio(metadataPanelContainer, 1.0f);
  }

  private void rebuildMetaDataPanels() {
    final List<MetadataPanel> metadataPanels = new ArrayList<>();
    int createdPanelsBySelectedMetadata = 0;
    for (final Metadata metadata : metadataPanelByMetadata.keySet()) {
      if (createdPanelsBySelectedMetadata < MAX_SELECTED_METADATA) {
        final MetadataPanel metadataPanel = buildMetadataPanelForMetadata(metadata);
        metadataPanels.add(metadataPanel);
        createdPanelsBySelectedMetadata++;
      }
    }
    refillMetadataPanelLayout(metadataPanels);
  }

  private void refillMetadataPanelLayout(List<MetadataPanel> metadataPanels) {
    metadataPanelLayout.removeAllComponents();
    for (final MetadataPanel metadataPanel : metadataPanels) {
      metadataPanelLayout.addComponent(metadataPanel);
    }
  }

  private MetadataPanel buildMetadataPanelForMetadata(final Metadata metadata) {
    final MetadataPanel metadataPanel = new MetadataPanel(metadata);
    metadataPanel.getRemoveButton().addClickListener(event -> {
      metadataPanelByMetadata.remove(metadata);
      final Set<Metadata> metadataSet = metadataPanelByMetadata.keySet();
      final ArrayList<Metadata> metadataList = new ArrayList<>(metadataSet);
      currentSelectionListIs(metadataList);
      notifySubscribersAboutUpdatedList(metadataList);
    });
    return metadataPanel;
  }

  @Override
  public void currentSelectionListIs(final List<Metadata> list) {
    metadataPanelByMetadata.clear();
    for (final Metadata metadata : list) {
      metadataPanelByMetadata.put(metadata, buildMetadataPanelForMetadata(metadata));
    }
    rebuildMetaDataPanels();
  }

  @Override
  public void notifySubscribersAboutUpdatedList(final List<Metadata> list) {
    for (final SelectionListSubscriber subscriber : subscribers) {
      subscriber.currentSelectionListIs(list);
    }
  }

  @Override
  public void addSubscriber(final SelectionListSubscriber subscriber) {
    subscribers.add(subscriber);
  }
}
