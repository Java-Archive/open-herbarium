package org.openherbarium.module.backend.rest.api.security.login;

import org.openherbarium.module.api.security.login.LoginService;

/**
 *
 */
public interface LoginServiceREST extends LoginService {

  //TODO technical Annotations
  @Override
  public Boolean checkLogin(String username , String password);
}
