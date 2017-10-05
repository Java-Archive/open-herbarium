package org.openherbarium.module.srv.imageservice.rest.endpoint.impl;

import org.apache.commons.io.IOUtils;
import org.openherbarium.module.srv.imageservice.rest.endpoint.api.ImageService;
import org.openherbarium.module.srv.imageservice.rest.endpoint.util.PathFinder;
import org.rapidpm.binarycache.api.BinaryCacheClient;
import org.rapidpm.binarycache.api.CacheByteArray;
import org.rapidpm.binarycache.api.Result;
import org.rapidpm.binarycache.api.defaultkey.DefaultCacheKey;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageServiceImpl implements ImageService {

  private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class.getSimpleName());
  private static final String CACHE_NAME = "default";
  private static final String IMAGE_PROPERTIES_XML = "ImageProperties.xml";
  private static final String IMAGE_FOLDER_PROPERTY = "images";

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
  public byte[] getImage(String imageId, String tileGroup, String image) {
    final DefaultCacheKey cacheKey = new DefaultCacheKey(imageId + "_" + tileGroup + "_" + image);
    final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, cacheKey);
    if (cachedElement.isPresent()) {
      return cachedElement.get().byteArray;
    } else {
      return tryToLoadImageFromDisk(imageId, tileGroup, image, cacheKey);
    }
  }

  private byte[] tryToLoadImageFromDisk(String imageId, String tileGroup, String image, DefaultCacheKey cacheKey) {
    try {
      final Path imageFolder = getBasePath();
      final Path path = Paths.get(imageFolder.toString(), imageId, tileGroup, image);
      final byte[] bytes = IOUtils.toByteArray(path.toUri());
      cacheLoadedImage(imageId, cacheKey, bytes);
      return bytes;

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      return loadDummyImage();
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
    final String path = System.getProperty(IMAGE_FOLDER_PROPERTY, "_data/example_images");
    return Paths.get(path);
  }

  private byte[] loadDummyImage() {
    try {
      final Path path = Paths.get(getClass().getResource("404.jpg").toURI());
      return Files.readAllBytes(path);
    } catch (URISyntaxException | IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      return new byte[0];
    }
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
