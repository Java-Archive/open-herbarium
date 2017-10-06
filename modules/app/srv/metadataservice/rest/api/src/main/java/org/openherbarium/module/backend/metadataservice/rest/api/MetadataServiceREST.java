package org.openherbarium.module.backend.metadataservice.rest.api;

import org.openherbarium.module.backend.metadataservice.api.MetadataService;

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



}
