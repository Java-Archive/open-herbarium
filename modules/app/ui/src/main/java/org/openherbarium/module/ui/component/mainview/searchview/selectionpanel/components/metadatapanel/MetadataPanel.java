package org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel;

import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components.ImageGridLayout;
import org.openherbarium.module.ui.component.mainview.searchview.selectionpanel.components.metadatapanel.components.MetadataFormLayout;

public class MetadataPanel extends Panel {

    private VerticalLayout contentLayout;
    private MetadataFormLayout formLayout;
    private ImageGridLayout tasksAndThumbnailLayout;

    public MetadataPanel(final Metadata metadata) {
        contentLayout = new VerticalLayout();
        formLayout = new MetadataFormLayout(metadata);
        tasksAndThumbnailLayout = new ImageGridLayout(metadata);
        contentLayout.setSizeFull();
        contentLayout.addComponents(formLayout, tasksAndThumbnailLayout);
        setSizeFull();
        setContent(contentLayout);
    }

    public Button getRemoveButton() {
        return tasksAndThumbnailLayout.getRemoveButton();
    }

}
