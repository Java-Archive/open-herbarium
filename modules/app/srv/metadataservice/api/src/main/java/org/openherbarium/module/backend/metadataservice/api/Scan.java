package org.openherbarium.module.backend.metadataservice.api;

import net.vergien.beanautoutils.annotation.Bean;

@Bean
public class Scan {
  private long id;
  private String name;

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
  public int hashCode() {
    return ScanBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return ScanBeanUtil.doEquals(this, obj);
  }

  @Override
  public String toString() {
    return ScanBeanUtil.doToString(this);
  }
}
