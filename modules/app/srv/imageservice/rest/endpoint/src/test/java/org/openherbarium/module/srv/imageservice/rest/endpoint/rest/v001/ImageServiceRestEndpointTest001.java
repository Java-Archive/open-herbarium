package org.openherbarium.module.srv.imageservice.rest.endpoint.rest.v001;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.rest.BaseRestTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageServiceRestEndpointTest001 extends BaseRestTest {

  @Test
  void test001() {
    Client client = ClientBuilder.newClient();
    final String target = String.format(genereateBaseRestUrl() + "/%d/%s/%s", 47659, "TileGroup0", "0-0-0.jpg");
    System.out.println("target = " + target);

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

  @Test
  void test002() throws Exception {
    Client client = ClientBuilder.newClient();
    final String target = String.format(genereateBaseRestUrl() + "/%d/%s", 47659, "properties");
    System.out.println("target = " + target);

    Response response = client
        .target(target)
        .request(MediaType.TEXT_XML)
        .get();

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    final byte[] entity = response.readEntity(byte[].class);
    assertNotNull(entity);

    final File file = new File("_data/example_images/Care_bohe_GFW_47659/ImageProperties.xml");
    assertEquals(file.length(), entity.length);
    assertEquals(FileUtils.readFileToString(file, "UTF-8"), new String(entity));
  }

}