package org.openherbarium.module.backend.metadataservice.api;

import java.util.List;

/**
 *
 */
public interface MetadataService {

  public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder);

  public long count(String taxon, String determiner, String recorder);
}
