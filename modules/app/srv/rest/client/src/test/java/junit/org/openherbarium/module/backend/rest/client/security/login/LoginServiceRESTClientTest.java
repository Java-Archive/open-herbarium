package junit.org.openherbarium.module.backend.rest.client.security.login;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.backend.rest.client.security.login.LoginServiceRESTClient;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;

/**
 *
 */
public class LoginServiceRESTClientTest {


  @Test
  @Disabled
    //expecting that a service is running
  void test001() {
    final LoginServiceRESTClient restClient = DI.activateDI(LoginServiceRESTClient.class);
    Assert.assertTrue(restClient.checkLogin("admin" , "admin"));
  }

  //TODO all of this has to move in some Basic Test
  @BeforeEach
  void setUp() {
    PortUtils portUtils = new PortUtils();
    System.setProperty(REST_HOST_PROPERTY , "127.0.0.1");
    System.setProperty(REST_PORT_PROPERTY , 7081 + "");
//    System.setProperty(REST_PORT_PROPERTY , portUtils.nextFreePortForTest() + "");

    DI.clearReflectionModel();

    DI.activatePackages("org.rapidpm");
    DI.activatePackages("org.openherbarium");
    DI.activatePackages(this.getClass());

//    Main.deploy();

  }

  @AfterEach
  void tearDown() {
    Main.stop();
    DI.clearReflectionModel();
  }


}
