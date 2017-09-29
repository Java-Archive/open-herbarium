package org.openherbarium.module.backend.rest.client.security.login;

import static java.util.Objects.requireNonNull;
import static org.rapidpm.frp.Transformations.not;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.security.login.LoginService;
import com.google.gson.Gson;

/**
 *
 */
public class LoginServiceRESTClient implements LoginService , HasLogger {


  private String port = System.getProperty("org.openherbarium.rest.port");
  private String ip = System.getProperty("org.openherbarium.rest.host");

  //TODO extract BaseURLGenerator
  @Override
  public Boolean checkLogin(String username , String password) {

    //PreCheck
//    PropertyService
    requireNonNull(username , "username must not null");
    requireNonNull(password , "password must not null");

    Client client = ClientBuilder.newClient();
    final Invocation.Builder request = client
        //TODO make it more generic
        .target("http://" + ip + ":" + port + "/rest/v001/security/login")
        .path("checkLogin")
        .queryParam("username" , username)
        .queryParam("password" , password)
        .request();
    final Response response = request.get();

    if (not().apply(200 == response.getStatus())) {
      return Boolean.FALSE; //TODO maybe with an Result to give more informations ?
    }
//    Assert.assertEquals("OK" , response.getStatusInfo().toString());
    final String data = response.readEntity(String.class);
    client.close();
    return new Gson().fromJson(data , Boolean.class);
  }


}
