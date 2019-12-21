package org.openherbarium.webapp;

import org.apache.commons.cli.ParseException;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.nano.CoreUIServiceJava;

public class UIService
    extends CoreUIServiceJava {

  static Result<UIService> uiService = Result.failure("not initialized");

  public static void main(String[] args) throws ParseException {
    System.setProperty(CORE_UI_BASE_PKG, "org.openherbarium");


    CoreUIServiceJava uiService = new UIService().executeCLI(args);
    uiService.startup();
    UIService.uiService = Result.success((UIService) uiService);


  }

}
