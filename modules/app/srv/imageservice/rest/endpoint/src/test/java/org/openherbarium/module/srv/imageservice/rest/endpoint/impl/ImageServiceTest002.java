package org.openherbarium.module.srv.imageservice.rest.endpoint.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.srv.imageservice.rest.endpoint.api.ImageService;
import org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants;
import org.rapidpm.ddi.DI;

import java.util.Optional;

import static org.junit.Assert.assertFalse;

public class ImageServiceTest002 {

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
    System.setProperty(ImageServiceConstants.IMAGE_FOLDER_PROPERTY, "notThere");
    imageService = DI.activateDI(ImageService.class);
    final Optional<byte[]> image = imageService.getImage("Care_bohe_GFW_47659", "TileGroup0", "0-0-0.jpg");

    assertFalse(image.isPresent());

    System.clearProperty(ImageServiceConstants.IMAGE_FOLDER_PROPERTY);
  }

}

