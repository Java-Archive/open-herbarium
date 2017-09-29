package org.openherbarium.module.ui.services.security.session;

import org.openherbarium.module.api.security.session.SessionService;
import org.openherbarium.module.api.security.user.User;
import com.vaadin.server.VaadinSession;

/**
 *
 */
public class SessionServiceImpl implements SessionService {
  @Override
  public boolean isUserPresent() {
    return
        (VaadinSession
             .getCurrent()
             .getAttribute(User.class) != null);
  }
}
