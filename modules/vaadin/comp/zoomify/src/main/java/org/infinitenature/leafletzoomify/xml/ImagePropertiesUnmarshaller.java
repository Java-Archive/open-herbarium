package org.infinitenature.leafletzoomify.xml;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.infinitenature.leafletzoomify.ZoomifyImageProperties;

public class ImagePropertiesUnmarshaller {
  private final JAXBContext jaxbContext;

  public ImagePropertiesUnmarshaller() throws JAXBException {
    jaxbContext = JAXBContext.newInstance(ZoomifyImageProperties.class);
  }

  public ZoomifyImageProperties readFromURL(URL url) throws JAXBException {
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (ZoomifyImageProperties) jaxbUnmarshaller.unmarshal(url);
  }
}
