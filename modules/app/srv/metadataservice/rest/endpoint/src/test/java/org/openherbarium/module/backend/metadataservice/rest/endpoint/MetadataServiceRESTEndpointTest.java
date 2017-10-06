package org.openherbarium.module.backend.metadataservice.rest.endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;

public class MetadataServiceRESTEndpointTest {
  @Before
  public void setUp() {
    DI.clearReflectionModel();
    DI.activatePackages("org.openherbarium");
    Main.deploy();
  }

  @After
  public void tearDown() {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test001() {







  }

}
