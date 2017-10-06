package org.openherbarium.module.ui.component.mainview.dashboard;

import java.net.MalformedURLException;

import javax.annotation.PostConstruct;

import org.infinitenature.leafletzoomify.LZoomifyImage;
import org.openherbarium.module.api.HasLogger;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;

/**
 *
 */
public class DashBoard extends Composite implements HasLogger {

  @PostConstruct
  private void postConstruct() {

    try {
      final LZoomifyImage zoomifyImage = new LZoomifyImage("http://cmulders.github.io/Leaflet.Zoomify/examples/hubble-orion-nebula" ,
                                                           "&copy; Waldzwerge");
      setCompositionRoot(zoomifyImage);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      setCompositionRoot(new Label("Hello Open Herbarium"));
    }
  }


}
