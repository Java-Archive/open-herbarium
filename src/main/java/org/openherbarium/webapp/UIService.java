package org.openherbarium.webapp;

import com.vaadin.flow.server.Constants;
import org.apache.commons.cli.ParseException;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.nano.CoreUIServiceJava;

import static com.vaadin.flow.server.Constants.SERVLET_PARAMETER_ENABLE_DEV_SERVER;

public class UIService
    extends CoreUIServiceJava {

  static Result<UIService> uiService = Result.failure("not initialized");

  public static void main(String[] args) throws ParseException {
    System.setProperty(Constants.SERVLET_PARAMETER_ENABLE_DEV_SERVER, "false");
    System.setProperty("vaadin.compatibilityMode", "false");
    System.setProperty("vaadin.productionMode", "true");
    System.setProperty(CORE_UI_BASE_PKG, "org.openherbarium");


    CoreUIServiceJava uiService = new UIService().executeCLI(args);
    uiService.startup();
    UIService.uiService = Result.success((UIService) uiService);


  }

}
