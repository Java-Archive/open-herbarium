package org.openherbarium.module.backend.metadataservice.mock.api;

import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataFilter;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

import java.util.List;

/**
 * only a few methods if you need additional stuff for REST
 */
public interface MetadataServiceMOCK extends MetadataService {

  String PATH_METHODE_FIND = "mockfind";
  String PATH_BASE = "metadataservice";

  @Override //TODO decide what will happen with Checkstyle
  List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset, MetadataFilter filter);
}
