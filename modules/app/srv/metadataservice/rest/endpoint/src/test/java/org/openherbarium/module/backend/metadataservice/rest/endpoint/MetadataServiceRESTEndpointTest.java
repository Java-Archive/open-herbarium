package org.openherbarium.module.backend.metadataservice.rest.endpoint;

import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.rapidpm.ddi.DI;

public class MetadataServiceRESTEndpointTest {
  @Before
  public void setUp() {
    DI.clearReflectionModel();
    DI.activatePackages("org.openherbarium");
  }

  @After
  public void tearDown() {
    DI.clearReflectionModel();
  }

  @Test
  public void test() {
    MetadataService endpoint = DI.activateDI(MetadataService.class);
    fail("Not yet implemented");
  }

}
