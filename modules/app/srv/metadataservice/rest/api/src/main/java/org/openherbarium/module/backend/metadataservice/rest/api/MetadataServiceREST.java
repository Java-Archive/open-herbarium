package org.openherbarium.module.backend.metadataservice.rest.api;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;

/**
 *
 */
@Path("metadataservice")
public interface MetadataServiceREST {

  @GET
  public List<Metadata> find(@NotNull @QueryParam("sortField") @DefaultValue("id") String sortField,
      @NotNull @QueryParam("sortOrder") @DefaultValue("ASC") SortOrder sortOrder,
      @Min(0) @QueryParam("limit") @DefaultValue("20") int limit,
      @Min(0) @QueryParam("offset") @DefaultValue("0") int offset);

}
