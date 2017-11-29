package org.openherbarium.module.srv.metadataservice.rest.endpoint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;


public class MetadataServiceRESTEndpoint implements MetadataServiceREST, HasLogger {

  public MetadataServiceRESTEndpoint() {
    logger().info("Create instance");
  }


  @Override

  public List<Metadata> find(SortField sortField, SortOrder sortOrder, int limit, int offset,
      String taxon, String determiner, String recorder, LocalDate from, LocalDate to) {
    logger().info("Call rest service endpoint.");
    final List<Metadata> result = new ArrayList<>();
    return result;
  }


  @Override
  public long count(String taxon, String determiner, String recorder) {
    logger().info("Call rest service endpoint.");
    return 0;
  }
}
