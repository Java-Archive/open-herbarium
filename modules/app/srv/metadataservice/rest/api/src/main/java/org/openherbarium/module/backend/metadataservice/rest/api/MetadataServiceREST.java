package org.openherbarium.module.backend.metadataservice.rest.api;

import java.util.List;
import javax.ws.rs.QueryParam;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;


public abstract class MetadataServiceREST implements MetadataService {


  @Override
  public abstract List<Metadata> find(@QueryParam("sortField") String sortField,
      @QueryParam("sortOrder") SortOrder sortOrder, @QueryParam("limit") int limit,
      @QueryParam("offset") int offset);
}
