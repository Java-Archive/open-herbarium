package org.openherbarium.module.backend.metadataservice.rest.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

/**
 * TODO this must be an interface again, but for now the di mechanism can't cope with JAX-RS
 * annotated interfaces
 */
@Path("/metadataservice")
public class MetadataServiceRESTEndpoint implements MetadataService, HasLogger {

  public MetadataServiceRESTEndpoint() {
    logger().info("Create instance");
  }

  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Metadata> find(@QueryParam("sortField") String sortField,
      @QueryParam("sortOrder") SortOrder sortOrder, @QueryParam("limit") int limit,
      @QueryParam("offset") int offset) {
    logger().info("Call rest service endpont.");
    List<Metadata> result = new ArrayList<>();
    result.add(new Metadata(1l, UUID.randomUUID(), "Abies alba", UUID.randomUUID()));
    return result;
  }
}
