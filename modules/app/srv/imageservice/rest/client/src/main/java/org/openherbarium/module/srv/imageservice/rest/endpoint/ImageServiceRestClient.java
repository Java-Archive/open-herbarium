package org.openherbarium.module.srv.imageservice.rest.endpoint;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class ImageServiceRestClient {

  public static final String PROPERTY_IP = "imageservice.ip";
  public static final String PROPERTY_PORT = "imageservice.port";
  public static final String DEFAULT_PORT = "8080";
  public static final String DEFAULT_IP = "127.0.0.1";

  private String serverIp;
  private String serverPort;

  @PostConstruct
  public void init() {
    this.serverIp = System.getProperty(PROPERTY_IP, DEFAULT_IP);
    this.serverPort = System.getProperty(PROPERTY_PORT, DEFAULT_PORT);
    if (Objects.isNull(serverIp)) throw new NullPointerException("serverIP is null");
    if (Objects.isNull(serverPort)) throw new NullPointerException("serverPort is null");
  }

  public String getImageProperties(String imageid) {
    final Client client = ClientBuilder.newClient();

    final Response response = client.target(buildBaseUrl() + imageid + "/" + "properties")
        .request()
        .get();

    return response.readEntity(String.class);
  }

  public byte[] getImage(String imageid, String tilegroup, String image) {
    final Client client = ClientBuilder.newClient();

    final Response response = client.target(buildBaseUrl() + imageid + "/" + tilegroup + "/" + image)
        .request()
        .get();

    return response.readEntity(byte[].class);
  }


  private String buildBaseUrl() {
    return String.format("http://%s:%s/rest/imageservice/", serverIp, serverPort);
  }
}
