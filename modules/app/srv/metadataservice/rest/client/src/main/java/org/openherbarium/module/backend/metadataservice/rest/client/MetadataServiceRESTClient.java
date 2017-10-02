package org.openherbarium.module.backend.metadataservice.rest.client;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.Metadata;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import xx.org.openherbarium.module.backend.metadataservice.rest.client.IMetadataServiceRest;

/**
 *
 */
public class MetadataServiceRESTClient implements MetadataService {

  @Inject
  private Configuration configuration;

  private ResteasyClient client;
  private ResteasyWebTarget target;
  private MetadataService delegate;

  @PostConstruct
  public void init() {
    client = new ResteasyClientBuilder().connectionPoolSize(10).build();
    target = client.target(configuration.getMetaServiceUrl());
    delegate = target.proxy(IMetadataServiceRest.class);
  }

  @Override
  public List<Metadata> find(String sortField, SortOrder sortOrder, int limit, int offset) {
    return delegate.find(sortField, sortOrder, limit, offset);
  }
}
