package org.infinitenature.leafletzoomify.xml;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;

import javax.xml.bind.JAXBException;

import org.infinitenature.leafletzoomify.ZoomifyImageProperties;
import org.junit.*;

public class ImagePropertiesUnmarshallerTest {

	private ImagePropertiesUnmarshaller imagePropertiesUnmarshaller;

	@Before
	public void setUp() throws JAXBException {
		imagePropertiesUnmarshaller = new ImagePropertiesUnmarshaller();
	}

	@Test
	public void test() throws JAXBException {
		final URL url = getClass().getResource("/ImageProperties.xml");
		ZoomifyImageProperties imageProperties = imagePropertiesUnmarshaller.readFromURL(url);

		assertThat(imageProperties.getWidth(), is(4000));
		assertThat(imageProperties.getHeight(), is(6000));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_nullURL() throws JAXBException {
		imagePropertiesUnmarshaller.readFromURL(null);
	}
}
