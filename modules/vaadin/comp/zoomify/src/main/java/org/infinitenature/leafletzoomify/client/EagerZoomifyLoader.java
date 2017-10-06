package org.infinitenature.leafletzoomify.client;

import org.peimari.gleaflet.zoomify.client.resources.LeafletZoomifyResourceInjector;
import com.google.gwt.core.client.EntryPoint;

public class EagerZoomifyLoader implements EntryPoint {

  @Override
  public void onModuleLoad() {
    LeafletZoomifyResourceInjector.ensureInjected();
  }

}
