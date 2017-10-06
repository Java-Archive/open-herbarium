package org.infinitenature.leafletzoomify;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.infinitenature.leafletzoomify.xml.ImagePropertiesUnmarshaller;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.shared.Crs;

/**
 * A vaadin component to display zoomify images.
 * <p>
 * <p>
 * Reads the with and height for the layer size from an URL.
 * <p>
 * <b>Note:</b> the data flow is a bit strange, since the xml info is read by
 * the server, but the image tiles are directly read from the users browser.
 */
public class LZoomifyImage extends LMap {

  private URL baseURL;
  private String attribution;
  private LZoomifiyLayer layer;
  private ImagePropertiesUnmarshaller imagePropertiesUnmarshaller;

  public LZoomifyImage() {
    super();
    try {
      this.imagePropertiesUnmarshaller = new ImagePropertiesUnmarshaller();
    } catch (JAXBException e) {
      throw new IllegalStateException("Failure creating xml unmarshaller" , e);
    }

  }

  public LZoomifyImage(String url , String copyrightInfo) throws MalformedURLException {
    this(new URL(url) , copyrightInfo);
  }

  public LZoomifyImage(URL url , String copyrightInfo) {
    this();
    this.baseURL = url;
    this.attribution = copyrightInfo;

    initLayer();
  }

  protected URL getXMLURL() {
    assertBaseURLNoNull();
    String url = baseURL.toString() + "/ImageProperties.xml";

    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new IllegalStateException(
          "The computed URL for the ImageProperties.xml file is not a valid url: " + url , e);
    }

  }

  protected String getTilesURL() {
    assertBaseURLNoNull();
    return baseURL.toString() + "/{g}/{z}-{x}-{y}.jpg";
  }

  private void assertBaseURLNoNull() {
    if (baseURL == null) {
      throw new IllegalStateException("The base URL must not be null");
    }
  }

  public void setBaseURL(URL baseURL , String attribution) {
    this.baseURL = baseURL;
    this.attribution = attribution;
    initLayer();
  }

  public void setBaseURL(URL baseURL) {
    this.baseURL = baseURL;
    initLayer();
  }

  public void setAttribution(String attribution) {
    this.attribution = attribution;
  }

  private void initLayer() {
    if (layer != null) {
      removeLayer(layer);
    }

    URL xmlUrl = getXMLURL();
    ZoomifyImageProperties properties;
    try {
      properties = imagePropertiesUnmarshaller.readFromURL(xmlUrl);
    } catch (JAXBException e) {
      throw new IllegalStateException("Failure reading image properties from " + xmlUrl , e);
    }
    String tilesUrl = getTilesURL();
    layer = new LZoomifiyLayer(tilesUrl , properties.getWidth() , properties.getHeight() , attribution);
    addLayer(layer);
    setCrs(Crs.Simple);
    setZoomLevel(0);
  }

}
