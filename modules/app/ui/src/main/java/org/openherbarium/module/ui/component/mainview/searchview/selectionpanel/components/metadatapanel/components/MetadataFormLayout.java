package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components;

import com.vaadin.data.Binder;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.openherbarium.module.backend.metadataservice.api.Metadata;

import static org.openherbarium.module.backend.metadataservice.api.Metadata.DATE;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.DETERMINER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.RECORDER;
import static org.openherbarium.module.backend.metadataservice.api.Metadata.TAXON_NAME;

public class MetadataFormLayout extends FormLayout {

    private Binder<Metadata> metadataBinder = new Binder<>();

    private final TextField taxonNameField = new TextField(TAXON_NAME);
    private final TextField recorderField = new TextField(RECORDER);
    private final TextField determinerField = new TextField(DETERMINER);
    private final DateField dateField = new DateField(DATE);

    public MetadataFormLayout(final Metadata metadata) {
        if (metadata == null) {
            return;
        }
        setWidth(100, Unit.PERCENTAGE);
//        setMargin(new MarginInfo(false, false,false,true));
        addComponents(taxonNameField, recorderField, determinerField, dateField);
        configureFields();
        setSpacing(false);
//        setComponentAlignment(taxonNameField, Alignment.MIDDLE_LEFT);
        configureBinder();
        metadataBinder.readBean(metadata);
        setAllFieldsReadOnly();
    }

    private void configureFields() {
        for (Component component : components) {
            if (component instanceof AbstractField) {
                final AbstractField abstractField = (AbstractField) component;
                abstractField.setWidth(100, Unit.PERCENTAGE);
                abstractField.addStyleName(ValoTheme.DATEFIELD_TINY);
            } else if (component instanceof Label) {
//                final Label label = (Label) component;
//                setComponentAlignment();
            }
        }
    }

    private void configureBinder() {
        metadataBinder.bind(taxonNameField, Metadata::getTaxonName, Metadata::setTaxonName);
        metadataBinder.bind(recorderField, theMetadata ->
                        theMetadata.getRecorder().getFirstName() + " " + theMetadata.getRecorder().getLastName(),
                (theMetadata, value) -> {
                }); // Setter wird nicht benoetigt
        metadataBinder.bind(determinerField, theMetadata ->
                        theMetadata.getDeterminer().getFirstName() + " " + theMetadata.getDeterminer().getLastName(),
                (theMetadata, value) -> {
                }); // Setter wird nicht benoetigt
        metadataBinder.bind(dateField, Metadata::getDate, Metadata::setDate);
    }

    private void setAllFieldsReadOnly() {
        for (final Component component : components) {
            if (component instanceof AbstractField) {
                final AbstractField abstractField = (AbstractField) component;
                abstractField.setReadOnly(true);
            }
        }
    }

}
