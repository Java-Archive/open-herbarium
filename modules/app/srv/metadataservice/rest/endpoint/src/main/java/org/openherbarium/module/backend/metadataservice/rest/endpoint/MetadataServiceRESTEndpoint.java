package org.openherbarium.module.backend.metadataservice.rest.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 *
 */
public class MetadataServiceRESTEndpoint implements MetadataServiceREST, MetadataService {

  public MetadataServiceRESTEndpoint() {
    logger().info("Create instance");
  }
  @Override
  public List<Metadata> find(@NotNull String sortField, @NotNull SortOrder sortOrder,
      @Min(0) int limit, @Min(0) int offset) {
    logger().info("Call rest service endpont.");
    return new ArrayList<>();
  }
}
