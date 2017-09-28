package org.openherbarium.module.backend.rest.endpoint.security.login;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.openherbarium.module.api.security.login.LoginService;
import com.google.gson.Gson;

/**
 *
 */
@Path("/v001/security/login")
public class LoginServiceREST {


  @Inject private LoginService loginService;

  @GET()
  @Path("{checkLogin}")
  @Produces(MediaType.APPLICATION_JSON)
  public String checkLogin(@QueryParam("username") final String username ,
                           @QueryParam("password") final String password) {

    return new Gson().toJson(loginService.checkLogin(username , password) , Boolean.class);
  }

}
