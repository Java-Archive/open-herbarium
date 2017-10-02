package org.openherbarium.module.backend.metadataservice.rest.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.openherbarium.module.backend.metadataservice.rest.api.MetadataServiceREST;

/**
 *
 */
public class MetadataServiceRESTClient {

  private final ResteasyClient client;
  private final ResteasyWebTarget target;

  MetadataServiceRESTClient(String serverURL) {

    client = new ResteasyClientBuilder().connectionPoolSize(10).build();
    target = client.target(serverURL);
  }

  public static MetadataServiceRESTClient getInstance(String serverURL) {
    return new MetadataServiceRESTClient(serverURL);
  }

  public MetadataServiceREST metaDataService() {
    return target.proxy(MetadataServiceREST.class);
  }
}
