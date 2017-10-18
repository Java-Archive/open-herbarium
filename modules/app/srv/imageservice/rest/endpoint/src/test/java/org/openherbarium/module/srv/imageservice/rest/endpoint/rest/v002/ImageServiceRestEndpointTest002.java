package org.openherbarium.module.srv.imageservice.rest.endpoint.rest.v002;

import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.rest.BaseRestTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ImageServiceRestEndpointTest002 extends BaseRestTest {


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
