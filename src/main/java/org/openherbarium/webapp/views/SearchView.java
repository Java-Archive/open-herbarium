package org.openherbarium.webapp.views;

import org.openherbarium.webapp.model.MetadataService;
import org.openherbarium.webapp.model.impl.MetadataServiceMockImpl;
import org.openherbarium.webapp.views.searchgrid.MetadataDataProvider;
import org.openherbarium.webapp.views.searchgrid.SearchGrid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = "search", layout = MainView.class)
public class SearchView extends Div {
  private static final MetadataService METADATA_SERVICE = new MetadataServiceMockImpl();
  private SearchGrid searchGrid = new SearchGrid();
  private MetadataDataProvider dataProvider = new MetadataDataProvider(METADATA_SERVICE);

  public SearchView() {
    setHeight("100%");
    searchGrid.setDataProvider(dataProvider);

    add(new Span("Filter view"));
    add(searchGrid);
  }
}
