package org.openherbarium.module.ui.services.security.role;


import org.openherbarium.module.api.security.role.RoleService;

/**
 *
 */
public class RoleServiceInMemory implements RoleService {


  //TODO connect to persistence layer
  //TODO hold in sync to User data
  //TODO hold in sync to menuitems


  @Override
  public boolean hasRole(String login , String requestedRole) {
    return true;
  }
}
