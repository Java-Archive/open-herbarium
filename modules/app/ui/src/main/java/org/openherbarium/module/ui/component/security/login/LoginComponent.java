package org.openherbarium.module.ui.component.security.login;

import static com.vaadin.ui.Notification.show;
import static org.openherbarium.module.ui.SessionAttributes.SESSION_ATTRIBUTE_USER;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.buttonID;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.passwordID;
import static org.openherbarium.module.vaadin.generic.ComponentIDGenerator.textfieldID;
import static org.rapidpm.ddi.DI.activateDI;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.api.property.PropertyService;
import org.openherbarium.module.api.security.login.LoginService;
import org.openherbarium.module.api.security.user.UserService;
import org.openherbarium.module.ui.component.mainview.MainView;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class LoginComponent extends Composite implements HasLogger {

  public static final String BUTTON_CAPTION_OK = "generic.ok";
  public static final String BUTTON_CAPTION_CANCEL = "generic.cancel";
  public static final String TEXTFIELD_CAPTION_LOGIN = "login.username";
  public static final String PASSWORDFIELD_CAPTION_PASSWORD = "login.password";
  public static final String PANEL_CAPTION_MAIN = "login.info";


  public static final String ID_BUTTON_OK = buttonID().apply(LoginComponent.class , BUTTON_CAPTION_OK);
  public static final String ID_BUTTON_CANCEL = buttonID().apply(LoginComponent.class , BUTTON_CAPTION_CANCEL);
  public static final String ID_TEXTFIELD_LOGIN = textfieldID().apply(LoginComponent.class , TEXTFIELD_CAPTION_LOGIN);
  public static final String ID_PASSWORDFIELD_PASSWORD = passwordID().apply(LoginComponent.class , PASSWORDFIELD_CAPTION_PASSWORD);
  public static final String ID_PANEL_MAIN = passwordID().apply(LoginComponent.class , PANEL_CAPTION_MAIN);


  @Inject private LoginService loginService;
  @Inject private UserService userService;
  @Inject private PropertyService propertyService;

  private final TextField login = new TextField();
  private final PasswordField password = new PasswordField();
  private final Button ok = new Button();
  private final Button cancel = new Button();
  private final HorizontalLayout buttons = new HorizontalLayout(ok , cancel);

  private final VerticalLayout layout = new VerticalLayout(login , password , buttons);
  private final Panel panel = new Panel(layout);

  public LoginComponent() {
    setCompositionRoot(panel);
  }

  @PostConstruct
  public void postProcess() {
    panel.setId(ID_PANEL_MAIN);
    panel.setCaption(property(PANEL_CAPTION_MAIN));
    panel.setSizeFull();

    login.setId(ID_TEXTFIELD_LOGIN);
    login.setCaption(property(TEXTFIELD_CAPTION_LOGIN)); //TODO i18n

    password.setId(ID_PASSWORDFIELD_PASSWORD);
    password.setCaption(property(PASSWORDFIELD_CAPTION_PASSWORD)); //TODO i18n

    ok.setCaption(property(BUTTON_CAPTION_OK));
    ok.setId(ID_BUTTON_OK);
    ok.addClickListener((Button.ClickListener) event -> {
      final String loginValue = login.getValue();
      final String passwordValue = password.getValue();
      clearInputFields();

      if (loginService.checkLogin(loginValue , passwordValue)) {
        final UI currentUI = UI.getCurrent();
        final VaadinSession vaadinSession = currentUI.getSession();
        userService
            .loadUser(loginValue)
            .ifPresentOrElse(
                (u) -> {
                  vaadinSession.setAttribute(SESSION_ATTRIBUTE_USER , u);
                  currentUI.setContent(activateDI(MainView.class));
                } ,
                (msg) -> {
                  vaadinSession.setAttribute(SESSION_ATTRIBUTE_USER , null);
                  currentUI.setContent(this);
                });
      } else {
        show(property("login.failed") ,
             property("login.failed.description") ,
             Notification.Type.WARNING_MESSAGE);
      }

    });

    cancel.setId(ID_BUTTON_CANCEL);
    cancel.setCaption(property(BUTTON_CAPTION_CANCEL));
    cancel.addClickListener((Button.ClickListener) event -> clearInputFields());
  }

  private void clearInputFields() {
    login.setValue("");
    password.setValue("");
  }

  private String property(String key) {
    return propertyService.resolve(key);
  }
}
