package org.openherbarium.module.srv.imageservice.rest.endpoint.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageServiceRestEndpointTest {

  @BeforeEach
  void setUp() {
    DI.activatePackages("org.openherbarium");

    final int portForTest = new PortUtils().nextFreePortForTest();
    System.setProperty(MainUndertow.REST_PORT_PROPERTY, String.valueOf(portForTest));
    System.setProperty(MainUndertow.REST_HOST_PROPERTY, "127.0.0.1");

    Main.deploy();
  }

  @AfterEach
  void tearDown() {
    Main.stop();
    System.clearProperty(MainUndertow.REST_PORT_PROPERTY);
    System.clearProperty(MainUndertow.REST_HOST_PROPERTY);
  }

  @Test
  void test001() {
    Client client = ClientBuilder.newClient();
    String baseUrl = basicRestUrl();
    final String target = String.format(baseUrl + "/%d/%s/%s", 47659, "TileGroup0", "0-0-0.jpg");
    System.out.println("adress = " + target);

    Response response = client
        .target(target)
        .request(MediaType.APPLICATION_OCTET_STREAM)
        .get();

    assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

    final byte[] entity = response.readEntity(byte[].class);
    assertNotNull(entity);

    final File file = new File("_data/example_images/Care_bohe_GFW_47659/TileGroup0/0-0-0.jpg");
    assertEquals(file.length(), entity.length);
  }

  private String basicRestUrl() {
    return String.format("http://%s:%s/rest/%s",
        System.getProperty(MainUndertow.REST_HOST_PROPERTY),
        System.getProperty(MainUndertow.REST_PORT_PROPERTY),
        "imageservice");
  }
}
