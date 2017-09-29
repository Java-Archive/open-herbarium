package junit.org.openherbarium.module.backend.rest.endpoint.security.login.v001;

import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.SERVLET_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.SERVLET_PORT_PROPERTY;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.backend.rest.endpoint.security.login.LoginServiceREST;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.test.RestUtils;
import com.google.gson.Gson;

/**
 *
 */
public class LoginServiceRESTTest {

  @Test @Disabled //TODO needed REST Endpoint started
  void test001() {
    final String basicReqURL = generateBasicReqURL(LoginServiceREST.class);
    System.out.println("basicReqURL = " + basicReqURL);

    Client client = ClientBuilder.newClient();
    final Invocation.Builder request = client
        .target(basicReqURL)
        .path("checkLogin")
        .queryParam("username" , "admin")
        .queryParam("password" , "admin")
        .request();
    final Response response = request.get();

    Assert.assertEquals(200 , response.getStatus());
    Assert.assertEquals("OK" , response.getStatusInfo().toString());
    final String data = response.readEntity(String.class);
    client.close();

    final Boolean checked = new Gson().fromJson(data , Boolean.class);
    Assert.assertTrue(checked);

  }

  //TODO all of this has to move in some Basic Test
  @BeforeEach
  void setUp() {
    PortUtils portUtils = new PortUtils();
    System.setProperty(REST_HOST_PROPERTY , "127.0.0.1");
    System.setProperty(SERVLET_HOST_PROPERTY , "127.0.0.1");
    System.setProperty(REST_PORT_PROPERTY , portUtils.nextFreePortForTest() + "");
    System.setProperty(SERVLET_PORT_PROPERTY , portUtils.nextFreePortForTest() + "");

    DI.clearReflectionModel();
    DI.activatePackages(this.getClass());

    Main.deploy();
  }

  @AfterEach
  void tearDown() {
    Main.stop();
    DI.clearReflectionModel();
  }


  public String generateBasicReqURL(Class restClass) {
    return new RestUtils().generateBasicReqURL(restClass , "/rest");
  }


}
