package org.openherbarium.webapp.model;

public class Scan implements Comparable<Scan> {
  private long id;
  private String name;

  public Scan() {
    super();
  }

  public Scan(long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(Scan scan) {
    return name.compareTo(scan.getName());
  }
}
