package org.openherbarium.webapp.model;

import java.time.LocalDate;

public class MetadataFilter {
  private String taxon;
  private String determiner;
  private String recorder;
  private LocalDate from;
  private LocalDate to;

  public MetadataFilter() {
    super();
  }

  public MetadataFilter(String taxon, String determiner, String recorder, LocalDate from,
      LocalDate to) {
    super();
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

  @Override
  public String toString() {
    return "MetadataFilter [taxon=" + taxon + ", determiner=" + determiner + ", recorder="
        + recorder + ", from=" + from + ", to=" + to + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((determiner == null) ? 0 : determiner.hashCode());
    result = prime * result + ((from == null) ? 0 : from.hashCode());
    result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
    result = prime * result + ((taxon == null) ? 0 : taxon.hashCode());
    result = prime * result + ((to == null) ? 0 : to.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MetadataFilter other = (MetadataFilter) obj;
    if (determiner == null) {
      if (other.determiner != null)
        return false;
    } else if (!determiner.equals(other.determiner))
      return false;
    if (from == null) {
      if (other.from != null)
        return false;
    } else if (!from.equals(other.from))
      return false;
    if (recorder == null) {
      if (other.recorder != null)
        return false;
    } else if (!recorder.equals(other.recorder))
      return false;
    if (taxon == null) {
      if (other.taxon != null)
        return false;
    } else if (!taxon.equals(other.taxon))
      return false;
    if (to == null) {
      if (other.to != null)
        return false;
    } else if (!to.equals(other.to))
      return false;
    return true;
  }
}
