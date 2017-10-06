package org.openherbarium.module.srv.imageservice.rest.endpoint;

import static org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST.PATH_BASE;

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
  public List<Metadata> find(@QueryParam(METHODE_FIND_QUERYPARAM_SORTFIELD) String sortField ,
                             @QueryParam(METHODE_FIND_QUERYPARAM_SORTORDER) SortOrder sortOrder ,
                             @QueryParam(METHODE_FIND_QUERYPARAM_LIMIT) int limit ,
                             @QueryParam(METHODE_FIND_QUERYPARAM_SORTOFFSET) int offset) {
    logger().info("Call rest service endpoint.");
    final List<Metadata> result = new ArrayList<>();
    result.add(new Metadata(1L , UUID.randomUUID() , "Abies alba" , UUID.randomUUID()));
    return result;
  }
}
