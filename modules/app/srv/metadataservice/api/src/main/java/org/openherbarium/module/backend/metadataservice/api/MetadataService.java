package org.openherbarium.module.backend.metadataservice.api;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface MetadataService {

  public enum SortField {
    RECORDER, DETERMINER, DATE, TAXON
  }

  public List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder, LocalDate from, LocalDate to);

  public long count(String taxon, String determiner, String recorder);
}
