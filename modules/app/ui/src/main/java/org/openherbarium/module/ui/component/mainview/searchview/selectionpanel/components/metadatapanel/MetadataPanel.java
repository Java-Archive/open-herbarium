package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.Scan;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components.ImageGridLayout;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components.MetadataFormLayout;

public class MetadataPanel extends Panel implements HasValue.ValueChangeListener {

  private VerticalLayout contentLayout;
  private MetadataFormLayout formLayout;
  private ImageGridLayout tasksAndThumbnailLayout;

  public MetadataPanel(final Metadata metadata) {
    contentLayout = new VerticalLayout();
    formLayout = new MetadataFormLayout(metadata);
    tasksAndThumbnailLayout = new ImageGridLayout(metadata);
    contentLayout.setSizeFull();
    contentLayout.addComponents(formLayout, tasksAndThumbnailLayout);
    formLayout.updateScanSelectionChangeListener(this);
    setSizeFull();
    setContent(contentLayout);
  }

  public Button getRemoveButton() {
    return tasksAndThumbnailLayout.getRemoveButton();
  }

  @Override
  public void valueChange(HasValue.ValueChangeEvent valueChangeEvent) {
    final Scan scan = (Scan) valueChangeEvent.getValue();
    tasksAndThumbnailLayout.updateThumbnailByScan(scan);
  }
}
