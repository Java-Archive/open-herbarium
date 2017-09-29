package org.openherbarium.module.api.security.session;

/**
 * Service for Session Access and Manipulation
 * Maybe working in an distributed Session System aka Hazelcast
 */
public interface SessionService {
  boolean isUserPresent();


}
