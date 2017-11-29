package org.openherbarium.module.backend.metadataservice.rest.api;

import static org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST.PATH_BASE;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
/**
 * only a few methods if you need additional stuff for REST
 */
@Path(PATH_BASE)
public interface MetadataServiceREST extends MetadataService {

  String PATH_BASE = "/metadataservice";
  String PATH_METHODE_FIND = "find";
  String PATH_METHODE_COUNT = "count";
  String METHODE_FIND_QUERYPARAM_SORTFIELD = "sortField";
  String METHODE_FIND_QUERYPARAM_SORTORDER = "sortOrder";
  String METHODE_FIND_QUERYPARAM_LIMIT = "limit";
  String METHODE_FIND_QUERYPARAM_SORTOFFSET = "offset";
  String METHODE_FIND_QUERYPARAM_TAXON = "taxon";
  String METHODE_FIND_QUERYPARAM_DETERMINER = "determiner";
  String METHODE_FIND_QUERYPARAM_RECORDER = "recorder";
  String METHODE_FIND_QUERYPARAM_BEFORE = "before";
  String METHODE_FIND_QUERYPARAM_AFTER = "after";



  @Override
  @GET
  @Path(PATH_METHODE_FIND)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Metadata> find(@QueryParam(METHODE_FIND_QUERYPARAM_SORTFIELD) SortField sortField,
      @QueryParam(METHODE_FIND_QUERYPARAM_SORTORDER) SortOrder sortOrder,
      @QueryParam(METHODE_FIND_QUERYPARAM_LIMIT) int limit,
      @QueryParam(METHODE_FIND_QUERYPARAM_SORTOFFSET) int offset,
      @QueryParam(METHODE_FIND_QUERYPARAM_TAXON) String taxon,
      @QueryParam(METHODE_FIND_QUERYPARAM_DETERMINER) String determiner,
      @QueryParam(METHODE_FIND_QUERYPARAM_RECORDER) String recorder,
      @QueryParam(METHODE_FIND_QUERYPARAM_AFTER) LocalDate from,
      @QueryParam(METHODE_FIND_QUERYPARAM_BEFORE) LocalDate to);

  @Override
  @GET
  @Path(PATH_METHODE_COUNT)
  @Produces(MediaType.APPLICATION_JSON)
  public long count(@QueryParam(METHODE_FIND_QUERYPARAM_TAXON) String taxon,
      @QueryParam(METHODE_FIND_QUERYPARAM_DETERMINER) String determiner,
      @QueryParam(METHODE_FIND_QUERYPARAM_RECORDER) String recorder);
}
