package org.openherbarium.module.backend.metadataservice.api;

import java.util.UUID;

public class Metadata {
  private long id;
  private UUID occurrenceUUID;
  private String taxon;
  private UUID imageId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UUID getOccurrenceUUID() {
    return occurrenceUUID;
  }

  public void setOccurrenceUUID(UUID occurrenceUUID) {
    this.occurrenceUUID = occurrenceUUID;
  }

  public String getTaxon() {
    return taxon;
  }

  public void setTaxon(String taxon) {
    this.taxon = taxon;
  }

  public UUID getImageId() {
    return imageId;
  }

  public void setImageId(UUID imageId) {
    this.imageId = imageId;
  }
}
