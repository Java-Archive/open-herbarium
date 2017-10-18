package org.openherbarium.module.backend.metadataservice.api;

import java.time.LocalDate;
import net.vergien.beanautoutils.annotation.Bean;

@Bean
public class MetadataFilter {
  private String taxon;
  private String determiner;
  private String recorder;
  private LocalDate before;
  private LocalDate after;

  public String getTaxon() {
    return taxon;
  }

  public void setTaxon(String taxon) {
    this.taxon = taxon;
  }

  public String getDeterminer() {
    return determiner;
  }

  public void setDeterminer(String determiner) {
    this.determiner = determiner;
  }

  public String getRecorder() {
    return recorder;
  }

  public void setRecorder(String recorder) {
    this.recorder = recorder;
  }

  public LocalDate getBefore() {
    return before;
  }

  public void setBefore(LocalDate before) {
    this.before = before;
  }

  public LocalDate getAfter() {
    return after;
  }

  public void setAfter(LocalDate after) {
    this.after = after;
  }

  @Override
  public int hashCode() {
    return MetadataFilterBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return MetadataFilterBeanUtil.doEquals(this, obj);
  }

  @Override
  public String toString() {
    return MetadataFilterBeanUtil.doToString(this);
  }
}
