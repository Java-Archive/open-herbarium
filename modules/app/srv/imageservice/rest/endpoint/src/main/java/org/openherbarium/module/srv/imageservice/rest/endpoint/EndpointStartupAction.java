package org.openherbarium.module.srv.imageservice.rest.endpoint;

import java.util.Optional;

import org.rapidpm.microservice.Main;

public class EndpointStartupAction implements Main.MainStartupAction {

  @Override
  public void execute(Optional<String[]> args) {
//    DI.registerClassForScope(BinaryCacheClient.class, JVMSingletonInjectionScope.class.getName());
  }
}
