package org.openherbarium.module.backend.metadataservice.api;

import java.time.LocalDate;
import java.util.Set;
import net.vergien.beanautoutils.annotation.Bean;

// TODO implementing QUAD breaks JSON deserialization because of missing default constructor
@Bean
public class Metadata {

  public static final String TAXON_NAME = "Taxon";
  public static final String RECORDER = "Finder";
  public static final String DETERMINER = "Bestimmer";
  public static final String DATE = "Datum";

  private long id;
  private String externalId;
  private String taxonName;
  private Person recorder;
  private Person determiner;
  private LocalDate date;
  private Set<Scan> scans;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getTaxonName() {
    return taxonName;
  }

  public void setTaxonName(String taxonName) {
    this.taxonName = taxonName;
  }

  public Person getRecorder() {
    return recorder;
  }

  public void setRecorder(Person finder) {
    this.recorder = finder;
  }

  public Person getDeterminer() {
    return determiner;
  }

  public void setDeterminer(Person determiner) {
    this.determiner = determiner;
  }

  public Set<Scan> getScans() {
    return scans;
  }

  public void setScans(Set<Scan> scans) {
    this.scans = scans;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public int hashCode() {
    return MetadataBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return MetadataBeanUtil.doEquals(this, obj);
  }

  @Override
  public String toString() {
    return MetadataBeanUtil.doToString(this);
  }

}
