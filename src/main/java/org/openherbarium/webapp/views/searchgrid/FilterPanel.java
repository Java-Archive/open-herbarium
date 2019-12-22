package org.openherbarium.webapp.views.searchgrid;

import org.openherbarium.webapp.model.MetadataFilter;
import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;

public class FilterPanel
    extends AbstractCompositeField<HorizontalLayout, FilterPanel, MetadataFilter> {

  private TextField taxonField = new TextField("Taxon");
  private TextField recorderField = new TextField("Finder");
  private TextField determinerField = new TextField("Bestimmer");
  private DatePicker fromDateField = new DatePicker("Von");
  private DatePicker toDateField = new DatePicker("Bis");
  private Button resetButton = new Button("Filter zurücksetzen");
  private Binder<MetadataFilter> binder = new Binder<>(MetadataFilter.class);

  public FilterPanel() {
    super(new MetadataFilter());
    getContent().setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    getContent().add(taxonField, recorderField, determinerField, fromDateField, toDateField,
        resetButton);
    resetButton.addClickListener(event -> resetFilter());

    binder.forField(taxonField).bind(MetadataFilter::getTaxon, MetadataFilter::setTaxon);
    binder.forField(recorderField).bind(MetadataFilter::getRecorder, MetadataFilter::setRecorder);
    binder.forField(determinerField).bind(MetadataFilter::getDeterminer,
        MetadataFilter::setDeterminer);
    binder.forField(fromDateField).bind(MetadataFilter::getFrom, MetadataFilter::setFrom);
    binder.forField(toDateField).bind(MetadataFilter::getTo, MetadataFilter::setTo);

    taxonField.setValueChangeMode(ValueChangeMode.EAGER);
    taxonField.addInputListener(event -> updateFilter());

    recorderField.setValueChangeMode(ValueChangeMode.EAGER);
    recorderField.addInputListener(event -> updateFilter());

    determinerField.setValueChangeMode(ValueChangeMode.EAGER);
    determinerField.addInputListener(event -> updateFilter());

    fromDateField.addValueChangeListener(event -> updateFilter());
    toDateField.addValueChangeListener(event -> updateFilter());

  }

  private void updateFilter() {
    MetadataFilter metadataFilter = new MetadataFilter();
    try {
      binder.writeBean(metadataFilter);
    } catch (ValidationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setModelValue(metadataFilter, true);
  }

  private void resetFilter() {
    binder.readBean(new MetadataFilter());
    setModelValue(new MetadataFilter(), false);
  }

  @Override
  protected void setPresentationValue(MetadataFilter metadataFilter) {
    binder.readBean(metadataFilter);
  }

}
