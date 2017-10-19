package org.openherbarium.module.srv.metadataservice.rest.endpoint;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {
  private static final ObjectMapper OM = init();

  @Override
  public ObjectMapper getContext(Class<?> arg0) {
    System.out.println("GET CONTEXT\n\n\n");
    return OM;
  }

  private static ObjectMapper init() {
    ObjectMapper om = new ObjectMapper();

    om.registerModule(new JavaTimeModule());
    return om;
  }
}
