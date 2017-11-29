package junit.org.openherbarium.module.backend.metadataservice.mock.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.backend.metadataservice.api.MetadataService;
import org.openherbarium.module.backend.metadataservice.api.MetadataService.SortField;
import org.openherbarium.module.backend.metadataservice.api.Person;
import org.openherbarium.module.backend.metadataservice.api.SortOrder;
import org.openherbarium.module.backend.metadataservice.mock.client.MetadataServiceMOCKClient;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;

public class MetadataServiceMOCKClientTest {

    private static int restPort;
    private static String host = "127.0.0.1";

    @BeforeEach
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

    @AfterEach
    public void tearDown() {
        Main.stop();
        DI.clearReflectionModel();
    }

    @Test
    public void test() {
        MetadataService client = DI.activateDI(MetadataServiceMOCKClient.class);
    System.out
        .println(client.find(SortField.TAXON, SortOrder.ASC, 1, 1, null, null, null, null, null));
    }

    @Test
    public void testFilterPerson() {
        MetadataServiceMOCKClient mockClient = new MetadataServiceMOCKClient();

        Person hansWurst = new Person("Hans", "Wurst");
        assertThat(mockClient.filter(hansWurst, ""), is(true));
        assertThat(mockClient.filter(hansWurst, "Hans"), is(true));
        assertThat(mockClient.filter(hansWurst, "WURST"), is(true));
        assertThat(mockClient.filter(hansWurst, "uRS"), is(true));
        assertThat(mockClient.filter(hansWurst, "ABC"), is(false));
    }

}
