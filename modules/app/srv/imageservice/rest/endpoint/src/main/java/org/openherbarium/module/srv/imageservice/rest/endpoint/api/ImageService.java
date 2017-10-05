package org.openherbarium.module.srv.imageservice.rest.endpoint;

import java.util.Optional;

public interface ImageService {
  byte[] getImage(String imageId, String tileGroup, String image);

  boolean isImageCached(String imageId, String tileGroup, String image);

  void clearCache();

  Optional<String> getImageProperties(String imageid);
}
