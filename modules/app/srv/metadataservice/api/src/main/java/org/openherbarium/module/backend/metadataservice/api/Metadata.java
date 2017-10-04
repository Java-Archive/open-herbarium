package org.openherbarium.module.backend.metadataservice.api;

import java.util.UUID;

// TODO implementing QUAD breaks JSON deserialization because of missing defualt constructor
public class Metadata {

  private long id;
  private UUID occurrenceUUID;
  private String taxon;
  private UUID imageId;

  public Metadata() {
    super();
  }

  public Metadata(long id, UUID occurrenceUUID, String taxon, UUID imageId) {
    super();
    this.id = id;
    this.occurrenceUUID = occurrenceUUID;
    this.taxon = taxon;
    this.imageId = imageId;
  }

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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Metadata [id=");
    builder.append(id);
    builder.append(", occurrenceUUID=");
    builder.append(occurrenceUUID);
    builder.append(", taxon=");
    builder.append(taxon);
    builder.append(", imageId=");
    builder.append(imageId);
    builder.append("]");
    return builder.toString();
  }


}
