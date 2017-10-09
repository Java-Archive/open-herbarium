package org.openherbarium.module.backend.metadataservice.rest.client;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 *
 */
public class MetadataServiceRESTClient implements MetadataServiceREST, HasLogger {

  @Inject private Configuration configuration;

  @PostConstruct
  public void init() { }

  private String targetURL() {
    final String targetURL = configuration.getMetaServiceUrl() + PATH_BASE + "/" + PATH_METHODE_FIND;
    logger().debug(" MetadataServiceRESTClient - target URL " + targetURL);

    System.out.println("targetURL = " + targetURL);
    return targetURL;
  }

  @Override
  public List<Metadata> find(String sortField , SortOrder sortOrder , int limit , int offset) {
    final Response response = new ResteasyClientBuilder()
        .build()
        .target(targetURL())
        .queryParam(METHODE_FIND_QUERYPARAM_SORTFIELD , sortField)
        .queryParam(METHODE_FIND_QUERYPARAM_LIMIT , limit)
        .queryParam(METHODE_FIND_QUERYPARAM_SORTOFFSET , offset)
        .queryParam(METHODE_FIND_QUERYPARAM_SORTORDER , sortOrder.name())
        .request()
        .get();


    logger().info("Result Status " + response.getStatusInfo().toString());

    return (response.hasEntity())
           ? response.readEntity(new GenericType<List<Metadata>>() { })
           : Collections.emptyList();

//    return delegate.find(sortField, sortOrder, limit, offset);
  }


}
