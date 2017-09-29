package org.openherbarium.module.ui.services.security.user;

import org.openherbarium.module.api.security.user.User;
import org.openherbarium.module.api.security.user.UserService;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class UserServiceInMemory implements UserService {

  //How to hold in sync with Shiro.ini ??? next part
  @Override
  public Result<User> loadUser(String login) {
    if (login.equals("admin")) return Result.success(new User("admin" , "Admin" , "Secure"));
    if (login.equals("max")) return Result.success(new User("max" , "Max" , "Rimkus"));
    if (login.equals("sven")) return Result.success(new User("sven" , "Sven" , "Ruppert"));
    return Result.failure("User for Login " + login + " not found");
  }

}
