package org.openherbarium.module.srv.imageservice.rest.endpoint.rest.v002;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.rest.BaseRestTest;
import org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ImageServiceRestEndpointTest002 extends BaseRestTest {

  @Override
  @BeforeEach
  protected void setUp() {
    super.setUp();
    System.setProperty(ImageServiceConstants.IMAGE_FOLDER_PROPERTY, "_data/example_images");
  }

  @Override
  @AfterEach
  protected void tearDown() {
    super.tearDown();
    System.clearProperty(ImageServiceConstants.IMAGE_FOLDER_PROPERTY);
  }

  @Test
  void test001() throws Exception {
    Client client = ClientBuilder.newClient();
    final String target = String.format(genereateBaseRestUrl() + "/%d/%s", 00000, "properties");
    System.out.println("target = " + target);

    Response response = client
        .target(target)
        .request(MediaType.TEXT_XML)
        .get();

    assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());

    final byte[] text = response.readEntity(byte[].class);
    assertEquals(0, text.length);
  }

}
