package org.openherbarium.webapp.model;

import java.time.LocalDate;

public class MetadataFilter {
  private String taxon;
  private String determiner;
  private String recorder;
  private LocalDate from;
  private LocalDate to;

  public MetadataFilter(String taxon, String determiner, String recorder, LocalDate from,
      LocalDate to) {
    this.taxon = taxon;
    this.determiner = determiner;
    this.recorder = recorder;
    this.from = from;
    this.to = to;
  }

  public String getDeterminer() {
    return determiner;
  }

  public LocalDate getFrom() {
    return from;
  }

  public String getRecorder() {
    return recorder;
  }

  public String getTaxon() {
    return taxon;
  }

  public LocalDate getTo() {
    return to;
  }

  public void setDeterminer(String determiner) {
    this.determiner = determiner;
  }

  public void setFrom(LocalDate from) {
    this.from = from;
  }

  public void setRecorder(String recorder) {
    this.recorder = recorder;
  }

  public void setTaxon(String taxon) {
    this.taxon = taxon;
  }

  public void setTo(LocalDate to) {
    this.to = to;
  }
}
