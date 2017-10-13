package org.openherbarium.module.srv.imageservice.rest.endpoint.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.api.ImageService;
import org.rapidpm.ddi.DI;

import java.util.Optional;

import static org.junit.Assert.*;

public class ImageServiceTest {

  private ImageService imageService;

  @BeforeEach
  void setUp() {
    DI.clearReflectionModel();
    DI.activatePackages("org.openherbarium");
  }

  @AfterEach
  void tearDown() {
    imageService.clearCache();
    DI.clearReflectionModel();
  }

  @Test
  void test001() {
    imageService = DI.activateDI(ImageService.class);
    final byte[] image = imageService.getImage("Care_bohe_GFW_47659", "TileGroup0", "0-0-0.jpg");

    assertTrue(image.length != 0);
  }

  @Test
  void test002() {
    imageService = DI.activateDI(ImageService.class);

    assertFalse(imageService.isImageCached("Care_bohe_GFW_47659", "TileGroup0", "0-0-0.jpg"));
    final byte[] image = imageService.getImage("Care_bohe_GFW_47659", "TileGroup0", "0-0-0.jpg");

    assertTrue(imageService.isImageCached("Care_bohe_GFW_47659", "TileGroup0", "0-0-0.jpg"));
  }

  @Test
  void test003() {
    imageService = DI.activateDI(ImageService.class);

    final Optional<String> imageProperties = imageService.getImageProperties("47659");
    assertTrue(imageProperties.isPresent());
    assertTrue(imageProperties.get().length() != 0);
    assertEquals("<IMAGE_PROPERTIES WIDTH=\"7217\" HEIGHT=\"8841\" NUMTILES=\"1386\" NUMIMAGES=\"1\" VERSION=\"1.8\" TILESIZE=\"256\" />",
        imageProperties.get());
  }

  @Test
  void test004() {
    imageService = DI.activateDI(ImageService.class);

    final Optional<String> imageProperties = imageService.getImageProperties("blub");
    assertFalse(imageProperties.isPresent());
  }

}

