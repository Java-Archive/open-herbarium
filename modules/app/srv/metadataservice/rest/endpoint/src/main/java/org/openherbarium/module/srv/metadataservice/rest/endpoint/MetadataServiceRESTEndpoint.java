package org.openherbarium.module.srv.metadataservice.rest.endpoint;

import static org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST.PATH_BASE;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 * TODO this must be an interface again, but for now the di mechanism can't cope with JAX-RS
 * annotated interfaces
 */
@Path(PATH_BASE)
public class MetadataServiceRESTEndpoint implements MetadataServiceREST, HasLogger {


  @Override
  @GET
  @Path(PATH_METHODE_FIND)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Metadata> find(@QueryParam(METHODE_FIND_QUERYPARAM_SORTFIELD) String sortField,
      @QueryParam(METHODE_FIND_QUERYPARAM_SORTORDER) SortOrder sortOrder,
      @QueryParam(METHODE_FIND_QUERYPARAM_LIMIT) int limit,
      @QueryParam(METHODE_FIND_QUERYPARAM_SORTOFFSET) int offset,
      @QueryParam(METHODE_FIND_QUERYPARAM_TAXON) String taxon,
      @QueryParam(METHODE_FIND_QUERYPARAM_DETERMINER) String determiner,
      @QueryParam(METHODE_FIND_QUERYPARAM_RECORDER) String recorder,
      @QueryParam(METHODE_FIND_QUERYPARAM_FROM) LocalDate from,
      @QueryParam(METHODE_FIND_QUERYPARAM_TO) LocalDate to) {
    logger().info("Call rest service endpoint.");
    final List<Metadata> result = new ArrayList<>();

    return result;
  }

  @Override
  @GET
  @Path(PATH_METHODE_COUNT)
  @Produces(MediaType.APPLICATION_JSON)
  public long count(@QueryParam(METHODE_FIND_QUERYPARAM_TAXON) String taxon,
      @QueryParam(METHODE_FIND_QUERYPARAM_DETERMINER) String determiner,
      @QueryParam(METHODE_FIND_QUERYPARAM_RECORDER) String recorder,
      @QueryParam(METHODE_FIND_QUERYPARAM_FROM) LocalDate from,
      @QueryParam(METHODE_FIND_QUERYPARAM_TO) LocalDate to) {
    logger().info("Call rest service endpoint.");
    return 0;
  }
}
