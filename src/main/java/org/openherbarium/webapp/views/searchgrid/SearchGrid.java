package org.openherbarium.webapp.views.searchgrid;

import org.openherbarium.webapp.model.Metadata;
import org.openherbarium.webapp.model.MetadataFilter;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;

public class SearchGrid extends Composite<Grid<Metadata>> {

  public static final String COLUMN_DATE = "date";
  public static final String COLUMN_RECORDER = "recorder";
  public static final String COLUMN_DETERMINER = "determiner";
  public static final String COLUMN_TAXON = "taxon";
  private Grid<Metadata> grid = new Grid<>(Metadata.class, false);
  private ConfigurableFilterDataProvider<Metadata, Void, MetadataFilter> filterDataProvider;

  @Override
  protected Grid<Metadata> initContent() {
    grid.addColumn(Metadata::getTaxonName, COLUMN_TAXON).setHeader("Taxon");
    grid.addColumn(m -> PersonFormatter.format(m.getDeterminer()), COLUMN_DETERMINER)
        .setHeader("Bestimmer");
    grid.addColumn(m -> PersonFormatter.format(m.getRecorder()), COLUMN_RECORDER)
        .setHeader("Finder");
    grid.addColumn(new LocalDateRenderer<>(Metadata::getDate, "dd.MM.yyyy"))
        .setSortProperty(COLUMN_DATE).setHeader("Datum");
    grid.setHeight("100%");
    return grid;
  }

  public void setDataProvider(MetadataDataProvider dataProvider) {
    filterDataProvider = dataProvider.withConfigurableFilter();
    grid.setDataProvider(filterDataProvider);
  }

  public void setFilter(MetadataFilter filter) {
    if (filterDataProvider != null) {
      filterDataProvider.setFilter(filter);
    }
  }
}
