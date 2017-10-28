package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.Scan;

import static org.openherbarium.module.backend.metadataservice.api.Metadata.DATE;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.DETERMINER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.RECORDER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.SCANS;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.TAXON_NAME;

public class MetadataFormLayout extends FormLayout {

  private final Metadata metadata;
  private Binder<Metadata> metadataBinder = new Binder<>();

  private final TextField taxonNameField = new TextField(TAXON_NAME);
  private final TextField recorderField = new TextField(RECORDER);
  private final TextField determinerField = new TextField(DETERMINER);
  private final DateField dateField = new DateField(DATE);
  private final ComboBox scanBox = new ComboBox(SCANS);

  public MetadataFormLayout(final Metadata metadata) {
    this.metadata = metadata;
    if (metadata == null) {
      return;
    }
    setWidth(100, Unit.PERCENTAGE);
    // setMargin(new MarginInfo(false, false,false,true));
    addComponents(taxonNameField, recorderField, determinerField, dateField, scanBox);
    configureFields();
    setSpacing(false);
    // setComponentAlignment(taxonNameField, Alignment.MIDDLE_LEFT);
    bindItemsToComponents();
    metadataBinder.readBean(metadata);
    metadataBinder.setReadOnly(true);
    scanBox.setSelectedItem(metadata.fetchDefaultScan());
    scanBox.setItemCaptionGenerator(o -> ((Scan) o).getName());
  }

  private void configureFields() {
    for (final Component component : components) {
      component.setWidth(100, Unit.PERCENTAGE);
      if (component instanceof AbstractField) {
        component.addStyleName(ValoTheme.DATEFIELD_TINY);
      } else if (component instanceof ComboBox) {
        component.addStyleName(ValoTheme.COMBOBOX_TINY);
        ((ComboBox) component).setEmptySelectionAllowed(false);
        ((ComboBox) component).setTextInputAllowed(false);
      }
    }
  }

  private void bindItemsToComponents() {
    metadataBinder.bind(taxonNameField, Metadata::getTaxonName, Metadata::setTaxonName);
    metadataBinder.bind(recorderField, theMetadata -> theMetadata.getRecorder().getFirstName() + " "
            + theMetadata.getRecorder().getLastName(), (theMetadata, value) -> {
    }); // Setter wird nicht benoetigt
    metadataBinder.bind(determinerField, theMetadata -> theMetadata.getDeterminer().getFirstName()
            + " " + theMetadata.getDeterminer().getLastName(), (theMetadata, value) -> {
    }); // Setter wird nicht benoetigt
    metadataBinder.bind(dateField, Metadata::getDate, Metadata::setDate);
    scanBox.setItems(metadata.fetchScansAsSortedList());
  }

  public void updateScanSelectionChangeListener(final HasValue.ValueChangeListener listener) {
    scanBox.addValueChangeListener(listener);
  }
}
