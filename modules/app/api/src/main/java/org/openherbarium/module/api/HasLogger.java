package org.openherbarium.module.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HasLogger is a feature interface that provides Logging capability for anyone
 * implementing it where logger needs to operate in serializable environment
 * without being static.
 */
//TODO move this to a core java module
public interface HasLogger {

  default Logger logger() {
    return LoggerFactory.getLogger(getClass());
  }
}
