package org.openherbarium.module.ui.services.property;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.openherbarium.module.api.property.PropertyService;


/**
 *
 */
public class PropertyServiceInMemory implements PropertyService {
  private final Map<String, String> storage = new HashMap<>();

  @Override
  public String resolve(final String key) {
    return storage.get(key);
  }

  @Override
  public boolean hasKey(final String key) {
    return storage.containsKey(key);
  }

  @PostConstruct
  public void init() {

    storage.put("admin", "admin");

    storage.put("generic.ok", "Ok");
    storage.put("generic.cancel", "Cancel");

    storage.put("login.name", "Login"); // i18n
    storage.put("login.info", "Please enter your username and password"); // i18n
    storage.put("login.username", "username"); // i18n
    storage.put("login.password", "password"); // i18n
    storage.put("login.failed", "Login failed..."); // i18n
    storage.put("login.failed.description", "Login failed, please use right User / Password combination"); // i18n

    storage.put("login.language.de", "German");
    storage.put("login.language.en", "English");

    storage.put("menu.point.dashboard", "Dashboard");
    storage.put("menu.point.calculate", "Calc");
    storage.put("menu.point.write", "Write");
    storage.put("menu.point.report", "Report");
    storage.put("menu.point.games.memory", "Games-Memory");
    storage.put("menu.point.exit", "Logout");
    storage.put("menu.point.exit.message", "You want to go?");

    }
}
