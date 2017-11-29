package org.openherbarium.module.srv.metadataservice.rest.endpoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyParamConverterProvider implements ParamConverterProvider {

  public MyParamConverterProvider() {
    System.out.println("Hello");
  }
  @Override
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType,
      Annotation[] annotations) {
    if (rawType.equals(LocalDate.class)) {
      return (ParamConverter<T>) new LocalDateConverter();
    }
    return null;
  }

}
