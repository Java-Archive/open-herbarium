package org.openherbarium.module.backend.metadataservice.rest.api;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

/**
 * TODO this must be an interface again, but for now the di mechanism can't cope with JAX-RS
 * annotated interfaces
 */
@Path("/metadataservice")
public abstract class MetadataServiceREST implements MetadataService {

  @Override
  @GET
  public abstract List<Metadata> find(@QueryParam("sortField") String sortField,
      @QueryParam("sortOrder") SortOrder sortOrder, @QueryParam("limit") int limit,
      @QueryParam("offset") int offset);
}
