package org.openherbarium.module.ui.component.mainview.searchview;

import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.ui.component.mainview.searchview.grid.SearchGrid;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;


/**
 *
 */
public class SearchView extends Composite implements HasLogger {

    @Inject
    private MetadataService metadataService;

    private HorizontalLayout mainLayout;

    private SearchGrid searchGrid;
    private VerticalLayout selectedItemsLayout;

    @PostConstruct
    private void postConstruct() {

        final List<Metadata> metadataList = metadataService.find("", SortOrder.ASC, 100, 0, null);
        searchGrid = new SearchGrid("Suche", metadataList);
        mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        selectedItemsLayout = new VerticalLayout();
        mainLayout.addComponentsAndExpand(searchGrid, selectedItemsLayout);
        mainLayout.setExpandRatio(searchGrid, 0.75f);
        mainLayout.setExpandRatio(selectedItemsLayout, 0.25f);
        setCompositionRoot(mainLayout);
    }

}
