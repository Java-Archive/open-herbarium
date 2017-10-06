package org.infinitenature.leafletzoomify;

import org.infinitenature.leafletzoomify.client.LeafletZoomifiyLayerState;
import org.vaadin.addon.leaflet.LTileLayer;

/**
 * A zoomifiy layer.
 */
public class LZoomifiyLayer extends LTileLayer {
  public LZoomifiyLayer(String url , int width , int height , String attribution) {
    super();
    setImageWidth(width);
    setImageHeight(height);
    setAttributionString(attribution);
    setUrl(url);
  }

  @Override
  protected LeafletZoomifiyLayerState getState() {
    return (LeafletZoomifiyLayerState) super.getState();
  }

  private void setImageHeight(int height) {
    getState().imageHeight = height;

  }

  private void setImageWidth(int width) {
    getState().imageWidth = width;
  }
}
