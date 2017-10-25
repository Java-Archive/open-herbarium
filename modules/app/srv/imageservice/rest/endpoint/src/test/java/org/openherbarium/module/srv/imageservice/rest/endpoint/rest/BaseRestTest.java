package org.openherbarium.module.srv.imageservice.rest.endpoint.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;

public class BaseRestTest {

  protected int port;

  @BeforeEach
  protected void setUp() {
    DI.activatePackages("org.openherbarium");
    DI.activatePackages("org.rapidpm");

    port = new PortUtils().nextFreePortForTest();
    System.setProperty(MainUndertow.REST_PORT_PROPERTY, String.valueOf(port));
    System.setProperty(MainUndertow.REST_HOST_PROPERTY, "127.0.0.1");

    Main.deploy();
  }

  @AfterEach
  protected void tearDown() {
    Main.stop();
    System.clearProperty(MainUndertow.REST_PORT_PROPERTY);
    System.clearProperty(MainUndertow.REST_HOST_PROPERTY);
  }

  protected String genereateBaseRestUrl(){
    return String.format("http://%s:%d/rest/imageservice", "127.0.0.1", port);
  }

}
