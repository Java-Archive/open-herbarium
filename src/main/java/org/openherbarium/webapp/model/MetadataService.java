package org.openherbarium.webapp.model;

import java.util.List;

public interface MetadataService {

  public enum SortField {
    RECORDER, DETERMINER, DATE, TAXON
  }

  public List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      MetadataFilter metadataFilter);

  public long count(MetadataFilter metadataFilter);
}
