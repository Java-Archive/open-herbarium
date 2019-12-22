package org.openherbarium.webapp.views.selection;

import org.openherbarium.webapp.model.Metadata;
import org.openherbarium.webapp.views.searchgrid.PersonFormatter;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PreviewComponent extends Composite<FormLayout> {

  private final Metadata metadata;
  private RemoveMetadataFromSelectionListener listener;

  public PreviewComponent(Metadata metadata, RemoveMetadataFromSelectionListener listener) {
    super();
    this.metadata = metadata;
    this.listener = listener;
    TextField taxonField = new TextField("Taxon", metadata.getTaxonName(), "");
    taxonField.setReadOnly(true);
    TextField recorderField =
        new TextField("Finder", PersonFormatter.format(metadata.getRecorder()), "");
    recorderField.setReadOnly(true);

    TextField determinerField =
        new TextField("Bestimmer", PersonFormatter.format(metadata.getDeterminer()), "");
    determinerField.setReadOnly(true);

    DatePicker dateField = new DatePicker(metadata.getDate());
    dateField.setLabel("Datum");
    dateField.setReadOnly(true);
    Button removeButton = new Button("Entfernen", event -> remove());
    getContent().add(taxonField, recorderField, determinerField, dateField, removeButton);
  }

  private void remove() {
    if (listener != null) {
      listener.removeMetadata(metadata);
    }
  }
}
