package org.openherbarium.module.srv.imageservice.rest.endpoint;

import org.rapidpm.binarycache.api.BinaryCacheClient;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.scopes.provided.JVMSingletonInjectionScope;
import org.rapidpm.microservice.Main;

import java.util.Optional;

public class EndpointStartupAction implements Main.MainStartupAction {

  @Override
  public void execute(Optional<String[]> args) {
//    DI.registerClassForScope(BinaryCacheClient.class, JVMSingletonInjectionScope.class.getName());
  }
}
