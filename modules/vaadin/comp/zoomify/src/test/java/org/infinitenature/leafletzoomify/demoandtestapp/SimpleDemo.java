package org.infinitenature.leafletzoomify.demoandtestapp;

import org.infinitenature.leafletzoomify.LZoomifiyLayer;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.shared.Crs;
import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.Component;

public class SimpleDemo extends AbstractTest {

	@Override
	public Component getTestComponent() {
		final LMap leafletMap = new LMap();
		leafletMap.setCrs(Crs.Simple);
		leafletMap.setHeight("100%");

		leafletMap.setView(-40d, 80d, 3d);

		LZoomifiyLayer layer = new LZoomifiyLayer(
				"http://cmulders.github.io/Leaflet.Zoomify/examples/hubble-orion-nebula/{g}/{z}-{x}-{y}.jpg", 6000,
				6000, "&copy; NASA, ESA, M. Robberto and the Hubble Space Telescope Orion Treasury Project Team");


		leafletMap.addLayer(layer);
		return leafletMap;
	}

	@Override
	public String getDescription() {
		return "Simple demo for the zoomify layer";
	}

}
