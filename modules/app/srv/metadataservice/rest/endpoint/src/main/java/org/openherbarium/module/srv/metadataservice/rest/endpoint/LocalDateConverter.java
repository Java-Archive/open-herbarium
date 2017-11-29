package org.openherbarium.module.srv.metadataservice.rest.endpoint;

import java.time.LocalDate;
import javax.ws.rs.ext.ParamConverter;

public class LocalDateConverter implements ParamConverter<LocalDate> {

  @Override
  public LocalDate fromString(String value) {
    return LocalDate.parse(value);
  }

  @Override
  public String toString(LocalDate value) {
    return value.toString();
  }

}
