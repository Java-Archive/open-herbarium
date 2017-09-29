package org.openherbarium.module.api.security.user;

import org.rapidpm.frp.model.Result;

/**
 *
 */
public interface UserService {
  Result<User> loadUser(String login);
}
