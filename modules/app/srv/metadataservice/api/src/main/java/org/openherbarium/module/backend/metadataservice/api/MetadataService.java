package org.openherbarium.module.backend.metadataservice.api;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface MetadataService {

  public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder, LocalDate from, LocalDate to);

  public long count(String taxon, String determiner, String recorder, LocalDate from, LocalDate to);
}
