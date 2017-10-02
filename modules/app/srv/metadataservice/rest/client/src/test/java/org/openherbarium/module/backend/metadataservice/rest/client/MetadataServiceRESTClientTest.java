package org.openherbarium.module.backend.metadataservice.rest.client;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;

public class MetadataServiceRESTClientTest {

  private static int restPort = 7081;
  private static String host = "127.0.0.1";
  @Before
  public void setUp() {


    System.setProperty(REST_HOST_PROPERTY, host);

    System.setProperty(REST_PORT_PROPERTY, restPort + "");
    DI.clearReflectionModel();

    DI.activatePackages("org.rapidpm");
    DI.activatePackages("org.openherbarium");
    DI.activatePackages(MetadataServiceRESTClientTest.class);

    Main.deploy();
  }

  @After
  public void teatDown() {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test() {
    MetadataService client = DI.activateDI(MetadataServiceRESTClient.class);
    System.out.println(client.find("id", SortOrder.ASC, 1, 1));
  }

  public static class TestConfiguration implements Configuration {

    @Override
    public String getMetaServiceUrl() {
      return "http://" + host + ":" + restPort + "/rest/";
    }

    @Override
    public String toString() {
      return getMetaServiceUrl();
    }
  }
}
