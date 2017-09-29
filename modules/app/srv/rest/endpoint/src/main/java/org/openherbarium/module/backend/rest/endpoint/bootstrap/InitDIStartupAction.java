package org.openherbarium.module.backend.rest.endpoint.bootstrap;

import java.util.Optional;

import org.openherbarium.module.api.HasLogger;
import org.rapidpm.microservice.Main;

/**
 *
 */
public class InitDIStartupAction implements Main.MainStartupAction , HasLogger {
  @Override
  public void execute(Optional<String[]> args) {
    logger().warn("InitDIStartupAction is executed for demo ");
  }
}
