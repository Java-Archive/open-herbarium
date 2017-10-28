package org.openherbarium.module.backend.metadataservice.api;

import net.vergien.beanautoutils.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

// TODO implementing QUAD breaks JSON deserialization because of missing default constructor
@Bean
public class Metadata {

  public static final String TAXON_NAME = "Taxon";
  public static final String RECORDER = "Finder";
  public static final String DETERMINER = "Bestimmer";
  public static final String DATE = "Datum";
  public static final String SCANS = "Scans";

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

  public void setRecorder(Person recorder) {
    this.recorder = recorder;
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

  public Scan fetchDefaultScan() {
      return fetchScansAsSortedList().get(0);
  }

  public List<Scan> fetchScansAsSortedList() {
    final Set<Scan> scans = getScans();
    if (scans != null && !scans.isEmpty()) {
      final List<Scan> scansList = new ArrayList<>(scans);
      Collections.sort(scansList);
      return scansList;
    }
    return new ArrayList<>();
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
