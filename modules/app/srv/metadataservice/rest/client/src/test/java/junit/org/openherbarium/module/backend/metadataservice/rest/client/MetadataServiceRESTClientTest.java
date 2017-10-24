package junit.org.openherbarium.module.backend.metadataservice.rest.client;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openherbarium.module.api.config.Configuration;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.MetadataService.SortField;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.rest.client.MetadataServiceRESTClient;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.ResponsibleFor;
import org.rapidpm.ddi.implresolver.ClassResolver;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;

public class MetadataServiceRESTClientTest {

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
    DI.activatePackages(MetadataServiceRESTClientTest.class);

    Main.deploy();
  }

  @After
  public void tearDown() {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test() {
    MetadataService client = DI.activateDI(MetadataServiceRESTClient.class);
        System.out.println(client.find(SortField.TAXON, SortOrder.ASC, 1, 1, null, null, null));
  }

  public static class TestConfiguration implements Configuration {

    @Override
    public String getMetaServiceUrl() {
      return "http://" + host + ":" + restPort + "/rest";
    }

    @Override
    public String toString() {
      return getMetaServiceUrl();
    }
  }

  @ResponsibleFor(Configuration.class)
  public static class ConfigurationClassResolver implements ClassResolver<Configuration> {

    @Override
    public Class<? extends Configuration> resolve(final Class<Configuration> interf) {
      return TestConfiguration.class;
    }
  }
}
