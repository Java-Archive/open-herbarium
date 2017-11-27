package org.openherbarium.module.srv.imageservice.rest.endpoint.impl;

import static org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants.CACHE_NAME;
import static org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants.IMAGE_FOLDER_PROPERTY;
import static org.openherbarium.module.srv.imageservice.rest.endpoint.util.ImageServiceConstants.IMAGE_PROPERTIES_XML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.openherbarium.module.srv.imageservice.rest.endpoint.api.ImageService;
import org.openherbarium.module.srv.imageservice.rest.endpoint.util.PathFinder;
import org.rapidpm.binarycache.api.BinaryCacheClient;
import org.rapidpm.binarycache.api.CacheByteArray;
import org.rapidpm.binarycache.api.Result;
import org.rapidpm.binarycache.api.defaultkey.DefaultCacheKey;

public class ImageServiceImpl implements ImageService {

  private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class.getSimpleName());

  @Inject
  private BinaryCacheClient cache;

  @Override
  public Optional<String> getImageProperties(String imageid) {
    final Path basePath = getBasePath();
    final Optional<Path> imagePath = PathFinder.find(basePath, imageid);

    if (!imagePath.isPresent()) {
      return Optional.empty();
    } else {
      return loadImageProperties(imagePath.get());
    }
  }

  private Optional<String> loadImageProperties(Path basePath) {
    final Path path = Paths.get(basePath.toString(), IMAGE_PROPERTIES_XML);
    try {
      final byte[] bytes = Files.readAllBytes(path);
      final String content = new String(bytes);
      return Optional.of(content);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return Optional.empty();
  }

  @Override
  public Optional<byte[]> getImage(String imageId, String tileGroup, String image) {
    final DefaultCacheKey cacheKey = new DefaultCacheKey(imageId + "_" + tileGroup + "_" + image);
    final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, cacheKey);
    if (cachedElement.isPresent()) {
      return Optional.ofNullable(cachedElement.get().getByteArray());
    } else {
      return tryToLoadImageFromDisk(imageId, tileGroup, image, cacheKey);
    }
  }

  private Optional<byte[]> tryToLoadImageFromDisk(String imageId, String tileGroup, String image, DefaultCacheKey cacheKey) {
    try {
      final Path imageFolder = getBasePath();
      final Optional<Path> imageIdPath = PathFinder.find(imageFolder, imageId);

      if (!imageIdPath.isPresent()) {
        return Optional.empty();
      } else {
        final Path imagePath = Paths.get(imageIdPath.get().toString(), tileGroup, image);
        final byte[] bytes = IOUtils.toByteArray(imagePath.toUri());
        cacheLoadedImage(imageId, cacheKey, bytes);
        return Optional.ofNullable(bytes);
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      return Optional.empty();
    }
  }

  private void cacheLoadedImage(String imageId, DefaultCacheKey cacheKey, byte[] bytes) {
    final CacheByteArray binary = new CacheByteArray(bytes);
    final Result result = cache.cacheBinary(CACHE_NAME, cacheKey, binary);

    if (result.equals(Result.FAILED)) {
      LOGGER.log(Level.SEVERE, String.format("failed to cache image for ID <%s>", imageId));
    }
  }

  private Path getBasePath() {
    final String path = System.getProperty(IMAGE_FOLDER_PROPERTY);
    return Paths.get(path);
  }

  @Override
  public boolean isImageCached(String imageId, String tileGroup, String image) {
    final DefaultCacheKey cacheKey = new DefaultCacheKey(imageId + "_" + tileGroup + "_" + image);
    final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, cacheKey);
    return cachedElement.isPresent();
  }

  @Override
  public void clearCache() {
    cache.clearCache(CACHE_NAME);
  }

}
