package org.openherbarium.webapp.model;

import java.util.Comparator;

public class Person implements Comparable<Person> {
  public Person() {
    super();
  }

  public Person(String firstName, String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private String firstName;
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int compareTo(Person person2) {
    return Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)
        .compare(this, person2);
  }
}
