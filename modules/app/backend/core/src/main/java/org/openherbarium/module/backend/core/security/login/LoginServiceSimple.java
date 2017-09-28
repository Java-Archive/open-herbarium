package org.openherbarium.module.backend.core.security.login;

import static java.util.Objects.isNull;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

import org.openherbarium.module.api.security.login.LoginService;
import org.rapidpm.frp.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LoginServiceSimple implements LoginService {


  private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceSimple.class);

  @Override
  public Boolean checkLogin(String username , String password) {

    final Result<Boolean> result = match(
        matchCase(() -> failure("username and password combination is wrong")) ,
        matchCase(() -> isNull(username) , () -> failure("Username is null")) ,
        matchCase(username::isEmpty , () -> failure("Username is empty")) ,
        matchCase(() -> isNull(password) , () -> failure("Password is null")) ,
        matchCase(password::isEmpty , () -> failure("Password is empty")) ,
        matchCase(() -> username.equals("admin") && password.equals("admin") , () -> success(Boolean.TRUE)) ,
        matchCase(() -> username.equals("user") && password.equals("user") , () -> success(Boolean.TRUE))
    );
    result.ifPresentOrElse(
        success -> LOGGER.info("User with username = " + username + " logged in ") ,
        failure -> LOGGER.warn("User with username = " + username + " login failed:  " + failure)
    );
    return result.getOrElse(() -> Boolean.FALSE);
  }
}
