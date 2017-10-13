package org.openherbarium.module.backend.metadataservice.api;

import java.util.Set;
import net.vergien.beanautoutils.annotation.Bean;

// TODO implementing QUAD breaks JSON deserialization because of missing default constructor
@Bean
public class Metadata {

  private long id;
  private String externalId;
  private String taxonName;
  private Person recorder;
  private Person determiner;
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
