package org.openherbarium.webapp.views;

import org.openherbarium.webapp.model.MetadataService;
import org.openherbarium.webapp.model.impl.MetadataServiceMockImpl;
import org.openherbarium.webapp.views.searchgrid.FilterPanel;
import org.openherbarium.webapp.views.searchgrid.MetadataDataProvider;
import org.openherbarium.webapp.views.searchgrid.SearchGrid;
import org.openherbarium.webapp.views.selection.SelectionPanel;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "search", layout = MainView.class)
public class SearchView extends HorizontalLayout {
  private static final MetadataService METADATA_SERVICE = new MetadataServiceMockImpl();
  private SearchGrid searchGrid = new SearchGrid();
  private FilterPanel filterPanel = new FilterPanel();
  private MetadataDataProvider dataProvider = new MetadataDataProvider(METADATA_SERVICE);
  private SelectionPanel selectionPanel = new SelectionPanel();

  public SearchView() {
    setHeight("100%");
    searchGrid.setDataProvider(dataProvider);
    filterPanel.addValueChangeListener(event -> searchGrid.setFilter(event.getValue()));
    add(new Div(filterPanel, searchGrid));
    searchGrid.setAddMetadataListener(selectionPanel);
    add(selectionPanel);
  }
}
