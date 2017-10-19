package org.openherbarium.module.backend.metadataservice.api;

import net.vergien.beanautoutils.annotation.Bean;

@Bean
public class Person {
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
  public int hashCode() {
    return PersonBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return PersonBeanUtil.doEquals(this, obj);
  }

  @Override
  public String toString() {
    return PersonBeanUtil.doToString(this);
  }

}
