package xx.org.openherbarium.module.backend.metadataservice.rest.client;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 * Workaround till "interface bug" is fixed. This must be outside of the scanned packages.
 * 
 * @see MetadataServiceREST
 */
@Path("/metadataservice")
public interface IMetadataServiceRest extends MetadataService {


  @Override
  @GET
  public List<Metadata> find(@QueryParam("sortField") String sortField,
      @QueryParam("sortOrder") SortOrder sortOrder, @QueryParam("limit") int limit,
      @QueryParam("offset") int offset);
}
