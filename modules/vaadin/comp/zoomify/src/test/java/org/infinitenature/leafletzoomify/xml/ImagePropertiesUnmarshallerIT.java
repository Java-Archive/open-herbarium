package org.infinitenature.leafletzoomify.xml;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.*;

import javax.xml.bind.JAXBException;

import org.infinitenature.leafletzoomify.ZoomifyImageProperties;
import org.junit.*;

public class ImagePropertiesUnmarshallerIT {

	private ImagePropertiesUnmarshaller imagePropertiesUnmarshaller;

	@Before
	public void setUp() throws JAXBException {
		imagePropertiesUnmarshaller = new ImagePropertiesUnmarshaller();
	}

	@Test
	public void testFromHttpSource() throws JAXBException, MalformedURLException {
		final URL url = new URL(
				"http://thematicmapping.org/playground/zoomify/books/ImageProperties.xml");
		ZoomifyImageProperties imageProperties = imagePropertiesUnmarshaller.readFromURL(url);

		assertThat(imageProperties.getWidth(), is(5472));
		assertThat(imageProperties.getHeight(), is(3648));
	}


}
