package org.openherbarium.module.ui.component.mainview.dashboard;

import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import org.infinitenature.leafletzoomify.LZoomifyImage;
import org.openherbarium.module.api.HasLogger;
import javax.annotation.PostConstruct;
import java.net.MalformedURLException;

/**
 *
 */
public class DashBoard extends Composite implements HasLogger {

  @PostConstruct
  private void postConstruct() {

    try {
      final LZoomifyImage zoomifyImage = new LZoomifyImage(
          "http://cmulders.github.io/Leaflet.Zoomify/examples/hubble-orion-nebula",
          "&copy; Waldzwerge");
      setCompositionRoot(zoomifyImage);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      setCompositionRoot(new Label("Hello Open Herbarium"));
    }
  }


}
