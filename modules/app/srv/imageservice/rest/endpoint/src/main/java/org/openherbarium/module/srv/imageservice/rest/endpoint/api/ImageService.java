package org.openherbarium.module.srv.imageservice.rest.endpoint.api;

import java.util.Optional;

public interface ImageService {
  Optional<String> getImageProperties(String imageid);

  Optional<byte[]> getImage(String imageId, String tileGroup, String image);

  boolean isImageCached(String imageId, String tileGroup, String image);

  void clearCache();
}
