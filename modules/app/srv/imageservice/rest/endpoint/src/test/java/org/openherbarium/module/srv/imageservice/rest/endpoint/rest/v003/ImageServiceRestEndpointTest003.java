package org.openherbarium.module.srv.imageservice.rest.endpoint.rest.v003;

import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.rest.BaseRestTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageServiceRestEndpointTest003 extends BaseRestTest {

  @Test
  void test001() {
    Client client = ClientBuilder.newClient();
    final String target = String.format(genereateBaseRestUrl() + "/%d/%s/%s", 00000, "TileGroup0", "0-0-0.jpg");
    System.out.println("target = " + target);

    Response response = client
        .target(target)
        .request(MediaType.APPLICATION_OCTET_STREAM)
        .get();

    assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());

    final byte[] entity = response.readEntity(byte[].class);
    assertNotNull(entity);
    assertEquals(0, entity.length);
  }


}
