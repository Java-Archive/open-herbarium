package org.openherbarium.module.backend.metadataservice.rest.endpoint;

import java.util.ArrayList;
import java.util.List;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 *
 */
public class MetadataServiceRESTEndpoint extends MetadataServiceREST implements HasLogger {

  public MetadataServiceRESTEndpoint() {
    logger().info("Create instance");
  }

  @Override
  public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset) {
    logger().info("Call rest service endpont.");
    return new ArrayList<>();
  }
}
