package junit.org.openherbarium.module.backend.metadataservice.mock.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openherbarium.module.backend.metadataservice.api.MetadataFilter;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.mock.client.MetadataServiceMOCKClient;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;

public class MetadataServiceMOCKClientTest {

  private static int restPort;
  private static String host = "127.0.0.1";
  @Before
  public void setUp() {
    restPort = new PortUtils().nextFreePortForTest();
    System.setProperty(REST_HOST_PROPERTY, host);

    System.setProperty(REST_PORT_PROPERTY, restPort + "");
    DI.clearReflectionModel();

    DI.activatePackages("org.rapidpm");
    DI.activatePackages("org.openherbarium");
    DI.activatePackages(MetadataServiceMOCKClientTest.class);

    Main.deploy();
  }

  @After
  public void tearDown() {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test() {
    MetadataService client = DI.activateDI(MetadataServiceMOCKClient.class);
    System.out.println(client.find("id", SortOrder.ASC, 1, 1, new MetadataFilter()));
  }

}
