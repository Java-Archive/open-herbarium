package org.openherbarium.module.srv.imageservice.rest.endpoint.util;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class PathFinder {

  private PathFinder() {
  }

  public static Optional<Path> find(Path base, String id) {
    try (Stream<Path> walk = Files.walk(base, 1, FileVisitOption.FOLLOW_LINKS)) {
      return walk.filter(path -> path.getFileName().toString().contains(id)).findFirst();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

}
