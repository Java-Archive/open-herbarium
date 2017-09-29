package org.openherbarium.module.api.security.role;

/**
 *
 */
public interface RoleService {
  public boolean hasRole(String login , String requestedRole);
}
