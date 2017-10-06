package org.infinitenature.leafletzoomify.demoandtestapp;

import java.net.MalformedURLException;

import org.infinitenature.leafletzoomify.LZoomifyImage;
import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;

public class SimpleLZoomifyImageDemo extends AbstractTest {

	@Override
	public Component getTestComponent() {
		try {
			return new LZoomifyImage("http://cmulders.github.io/Leaflet.Zoomify/examples/hubble-orion-nebula",
					"&copy; NASA, ESA, M. Robberto and the Hubble Space Telescope Orion Treasury Project Team");
		} catch (MalformedURLException e) {
			Notification.show("Failure creating LZoomifyImage", Type.ERROR_MESSAGE);
			return new Label();
		}
	}

	@Override
	public String getDescription() {
		return "Simple Demo using the LZoomifyImage component";
	}

}
