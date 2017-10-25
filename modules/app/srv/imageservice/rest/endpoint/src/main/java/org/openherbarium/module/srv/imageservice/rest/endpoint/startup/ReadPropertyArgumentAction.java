package org.openherbarium.module.srv.imageservice.rest.endpoint.startup;

import org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants;
import org.rapidpm.microservice.Main;

import java.util.Optional;

public class ReadPropertyArgumentAction implements Main.MainStartupAction {
  @Override
  public void execute(Optional<String[]> optional) {
    optional.ifPresent(arguments ->
        System.setProperty(ImageServiceConstants.IMAGE_FOLDER_PROPERTY, arguments[0]));
  }
}
