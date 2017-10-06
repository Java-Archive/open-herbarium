package org.infinitenature.leafletzoomify;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.net.*;

import org.junit.*;

public class LZoomifyImageTest {
	private LZoomifyImage lZoomifyImage = new LZoomifyImage();

	@Before
	public void setUp() throws MalformedURLException {
		lZoomifyImage.setBaseURL(new URL("http://thematicmapping.org/playground/zoomify/books"));
	}

	@Test
	public void testGetXMLURL() throws MalformedURLException {
		URL tilesURL = lZoomifyImage.getXMLURL();
		assertThat(tilesURL, is(new URL("http://thematicmapping.org/playground/zoomify/books/ImageProperties.xml")));
	}

	@Test
	public void testGetTilesURL() {
		String tilesURL = lZoomifyImage.getTilesURL();
		assertThat(tilesURL, is("http://thematicmapping.org/playground/zoomify/books/{g}/{z}-{x}-{y}.jpg"));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetXMLURL_noBaseURL() {
		lZoomifyImage.setBaseURL(null);
		lZoomifyImage.getXMLURL();
	}
}
