package org.openherbarium.module.srv.imageservice.rest.endpoint.util;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathFinder {
  private static final Logger LOGGER = LoggerFactory.getLogger(PathFinder.class);
  private PathFinder() {
  }

  public static Optional<Path> find(Path base, String id) {
    try (Stream<Path> walk = Files.walk(base, 1, FileVisitOption.FOLLOW_LINKS)) {
      return walk.filter(path -> path.getFileName().toString().contains(id)).findFirst();
    } catch (IOException e) {
      LOGGER.error("Failure finding path", e);
    }
    return Optional.empty();
  }

}
