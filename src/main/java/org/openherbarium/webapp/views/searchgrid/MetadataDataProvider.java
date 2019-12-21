package org.openherbarium.webapp.views.searchgrid;

import java.util.stream.Stream;
import org.openherbarium.webapp.model.Metadata;
import org.openherbarium.webapp.model.MetadataFilter;
import org.openherbarium.webapp.model.MetadataService;
import org.openherbarium.webapp.model.MetadataService.SortField;
import org.openherbarium.webapp.model.SortOrder;
import org.rapidpm.dependencies.core.logger.HasLogger;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.SortDirection;

public class MetadataDataProvider extends AbstractBackEndDataProvider<Metadata, MetadataFilter>
    implements HasLogger {
  private final MetadataService metadataService;

  public MetadataDataProvider(MetadataService metadataService) {
    super();
    this.metadataService = metadataService;
  }

  @Override
  protected Stream<Metadata> fetchFromBackEnd(Query<Metadata, MetadataFilter> query) {
    SortField sortField = getSortField(query);
    SortOrder sortOrder = getSortOrder(query);
    return metadataService
        .find(sortField, sortOrder, query.getLimit(), query.getOffset(), getFilter(query)).stream();
  }

  private SortOrder getSortOrder(Query<Metadata, MetadataFilter> query) {
    if (!query.getSortOrders().isEmpty()) {
      return query.getSortOrders().get(0).getDirection() == SortDirection.ASCENDING ? SortOrder.ASC
          : SortOrder.DESC;
    }
    return SortOrder.ASC;
  }

  private SortField getSortField(Query<Metadata, MetadataFilter> query) {
    if (!query.getSortOrders().isEmpty()) {
      switch (query.getSortOrders().get(0).getSorted()) {
        case SearchGrid.COLUMN_DATE:
          return SortField.DATE;
        case SearchGrid.COLUMN_DETERMINER:
          return SortField.DETERMINER;
        case SearchGrid.COLUMN_RECORDER:
          return SortField.RECORDER;
        case SearchGrid.COLUMN_TAXON:
        default:
          return SortField.TAXON;
      }
    }
    return SortField.TAXON;
  }

  @Override
  protected int sizeInBackEnd(Query<Metadata, MetadataFilter> query) {
    return (int) metadataService.count(getFilter(query));
  }

  private MetadataFilter getFilter(Query<Metadata, MetadataFilter> query) {
    return query.getFilter().isPresent() ? query.getFilter().get()
        : new MetadataFilter(null, null, null, null, null);
  }

}
