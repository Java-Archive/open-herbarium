package org.openherbarium.module.backend.metadataservice.rest.client;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.endpoint.MetadataServiceRESTEndpoint;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;

public class MetadataServiceRESTClientTest {

  private int restPort;
  private String host;
  @Before
  public void setUp() {
    PortUtils portUtils = new PortUtils();
    host = "127.0.0.1";
    System.setProperty(REST_HOST_PROPERTY, host);
    restPort = portUtils.nextFreePortForTest();
    System.setProperty(REST_PORT_PROPERTY, restPort + "");
    DI.clearReflectionModel();

    DI.activatePackages("org.rapidpm");
    DI.activatePackages(MetadataServiceRESTEndpoint.class);

    Main.deploy();
  }

  @After
  public void teatDown() {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  @Ignore
  public void test() {
    MetadataServiceRESTClient client =
        MetadataServiceRESTClient.getInstance("http://" + host + ":" + restPort + "/rest");
    System.out.println(client.metaDataService().find("id", SortOrder.ASC, 1, 1));
  }

}
