package org.openherbarium.module.backend.metadataservice.mock.api;

import java.time.LocalDate;
import java.util.List;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

/**
 * only a few methods if you need additional stuff for REST
 */
public interface MetadataServiceMOCK extends MetadataService {

  String PATH_METHODE_FIND = "mockfind";
  String PATH_BASE = "metadataservice";

  @Override //TODO decide what will happen with Checkstyle
  List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder, LocalDate from, LocalDate to);
}
