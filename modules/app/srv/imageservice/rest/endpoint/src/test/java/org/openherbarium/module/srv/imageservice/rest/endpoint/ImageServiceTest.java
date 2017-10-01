package org.openherbarium.module.srv.imageservice.rest.endpoint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rapidpm.binarycache.api.BinaryCacheClient;
import org.rapidpm.binarycache.api.CacheByteArray;
import org.rapidpm.binarycache.api.defaultkey.DefaultCacheKey;
import org.rapidpm.ddi.DI;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageServiceTest {

  @BeforeEach
  void setUp() {
    DI.clearReflectionModel();
    DI.activatePackages("org.openherbarium");
  }

  @AfterEach
  void tearDown() {
    DI.clearReflectionModel();
  }

  @Test
  void test001() throws Exception {
    final Path resource = Paths.get(this.getClass().getResource("GiantTwisters.jpg").toURI());
    final ImageService imageService = DI.activateDI(ImageService.class);

    imageService.loadImages(resource.getParent());
    final Optional<File> image = imageService.getImage(resource.getFileName().toString());

    assertTrue(image.isPresent());
    assertEquals(resource.toFile().length(), image.get().length());

  }
  public interface ImageService {

    void loadImages(Path path);

    Optional<File> getImage(String id);

  }

  public static class ImageServiceImpl implements ImageService {

    public static final String CACHE_NAME = "default";
    @Inject
    private BinaryCacheClient cache;

    @Override
    public void loadImages(Path path) {
      try (Stream<Path> paths = Files.walk(path, FileVisitOption.FOLLOW_LINKS)) {
        paths.filter(Files::isRegularFile)
            .forEach(this::cacheFile);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void cacheFile(Path file) {
      try {
        final DefaultCacheKey key = new DefaultCacheKey(file.getFileName().toString());
        final CacheByteArray value = new CacheByteArray(Files.readAllBytes(file));
        cache.cacheBinary(CACHE_NAME, key, value);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public Optional<File> getImage(String id) {
      final Optional<CacheByteArray> cachedElement = cache.getCachedElement(CACHE_NAME, new DefaultCacheKey(id));
      if (cachedElement.isPresent()) {
        try {
          final Path file = Files.write(Paths.get(id), cachedElement.get().byteArray);
          return Optional.ofNullable(file.toFile());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      return Optional.empty();
    }

  }

}

// TODO singleton
