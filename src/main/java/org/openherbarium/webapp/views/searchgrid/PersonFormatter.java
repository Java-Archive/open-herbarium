package org.openherbarium.webapp.views.searchgrid;

import org.apache.commons.lang3.StringUtils;
import org.openherbarium.webapp.model.Person;

public class PersonFormatter {
  private PersonFormatter() {
    throw new IllegalAccessError("Utility class");
  }

  public static String format(Person person) {
    if (person == null) {
      return "";
    } else {
      return StringUtils.join(new String[] {person.getFirstName(), person.getLastName()}, " ");
    }
  }
}
