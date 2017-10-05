package org.openherbarium.module.srv.imageservice.rest.endpoint;

import org.apache.commons.io.IOUtils;
import org.rapidpm.binarycache.api.BinaryCacheClient;
import org.rapidpm.binarycache.api.CacheByteArray;
import org.rapidpm.binarycache.api.defaultkey.DefaultCacheKey;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ImageServiceImpl implements ImageService {

  public static final String CACHE_NAME = "images";
  @Inject
  private BinaryCacheClient cache;

  @Override
  public Optional<String> getImageProperties(String imageid) {
    final Path basePath = getBasePath();
    final Path path = Paths.get(basePath.toString(), imageid, "ImageProperties.xml");
    try {
      final byte[] bytes = Files.readAllBytes(path);
      final String content = new String(bytes);
      return Optional.of(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public byte[] getImage(String imageId, String tileGroup, String image) {
    final DefaultCacheKey cacheKey = new DefaultCacheKey(imageId + "_" + tileGroup + "_" + "image");
    final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, cacheKey);
    if (cachedElement.isPresent()) {
      return cachedElement.get().byteArray;
    } else {
      // try to load image from disk
      final Path imageFolder = getBasePath();
      final Path path = Paths.get(imageFolder.toString(), imageId, tileGroup, image);

      try {
        final byte[] bytes = IOUtils.toByteArray(path.toUri());
        final CacheByteArray binary = new CacheByteArray(bytes);
        cache.cacheBinary(CACHE_NAME, cacheKey, binary);

        return bytes;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null; // image not found? -> return dummy image
  }

  private Path getBasePath() {
    return Paths.get("_data/example_images");
  }

  @Override
  public boolean isImageCached(String imageId, String tileGroup, String image) {
    final DefaultCacheKey cacheKey = new DefaultCacheKey(imageId + "_" + tileGroup + "_" + "image");
    final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, cacheKey);
    return cachedElement.isPresent();
  }

  @Override
  public void clearCache(){
    cache.clearCache(CACHE_NAME);
  }

}
