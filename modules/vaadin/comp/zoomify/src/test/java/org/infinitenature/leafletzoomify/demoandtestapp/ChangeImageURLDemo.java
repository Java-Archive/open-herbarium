package org.infinitenature.leafletzoomify.demoandtestapp;

import java.net.*;
import java.util.*;

import org.infinitenature.leafletzoomify.LZoomifyImage;
import org.vaadin.addonhelpers.AbstractTest;

import com.vaadin.ui.*;

public class ChangeImageURLDemo extends AbstractTest {

	static class ImageSource {
		private final String title;
		private final URL baseUrl;
		private final String attribution;

		public ImageSource(String title, URL baseUrl, String attribution) {
			super();
			this.title = title;
			this.baseUrl = baseUrl;
			this.attribution = attribution;
		}

		public String getTitle() {
			return title;
		}

		public URL getBaseUrl() {
			return baseUrl;
		}

		public String getAttribution() {
			return attribution;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	private List<ImageSource> imageSources;

	public ChangeImageURLDemo() throws MalformedURLException {
		imageSources = new ArrayList<>();
		imageSources.add(new ImageSource("Nebula",
				new URL("http://cmulders.github.io/Leaflet.Zoomify/examples/hubble-orion-nebula"),
				"&copy; NASA, ESA, M. Robberto and the Hubble Space Telescope Orion Treasury Project Team"));
		imageSources.add(new ImageSource("Books", new URL("http://thematicmapping.org/playground/zoomify/books/"),
				"Photo: Bjørn Sandvik"));
	}

	@Override
	public Component getTestComponent() {
		LZoomifyImage zoomifyImage = new LZoomifyImage();
		zoomifyImage.setSizeFull();

		RadioButtonGroup<ImageSource> imageSelection = new RadioButtonGroup<>("Select an image", imageSources);

		imageSelection.addValueChangeListener(event -> {
			ImageSource imageSource = event.getValue();

			zoomifyImage.setBaseURL(imageSource.getBaseUrl(), imageSource.getAttribution());
		});
		imageSelection.setValue(imageSources.get(0));
		final HorizontalLayout horizontalLayout = new HorizontalLayout(zoomifyImage, imageSelection);
		horizontalLayout.setSizeFull();
		return horizontalLayout;
	}

}
