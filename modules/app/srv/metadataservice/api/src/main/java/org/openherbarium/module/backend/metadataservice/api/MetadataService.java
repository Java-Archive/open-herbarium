package org.openherbarium.module.backend.metadataservice.api;

import java.util.List;

/**
 *
 */
public interface MetadataService {

  public enum SortField {
    RECORDER, DETERMINER, DATE, TAXON
  }

  public List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder);

  public long count(String taxon, String determiner, String recorder);
}
