package org.openherbarium.module.backend.metadataservice.rest.api;

import java.util.List;

import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

/**
 * only a few methods if you need additional stuff for REST
 */
public interface MetadataServiceREST extends MetadataService {


  String PATH_BASE = "/metadataservice";
  String PATH_METHODE_FIND = "find";
  String METHODE_FIND_QUERYPARAM_SORTFIELD = "sortField";
  String METHODE_FIND_QUERYPARAM_SORTORDER = "sortOrder";
  String METHODE_FIND_QUERYPARAM_LIMIT = "limit";
  String METHODE_FIND_QUERYPARAM_SORTOFFSET = "offset";

  @Override //TODO decide what will happen with Checkstyle
  List<Metadata> find(String sortField , SortOrder sortOrder , int limit , int offset);
}
