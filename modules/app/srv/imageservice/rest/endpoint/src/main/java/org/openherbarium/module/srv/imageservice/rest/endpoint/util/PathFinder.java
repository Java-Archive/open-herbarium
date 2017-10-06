package org.openherbarium.module.srv.imageservice.rest.endpoint.util;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class PathFinder {

  private PathFinder() {
  }

  public static Optional<Path> find(Path base, String id) {
    try {
      return Files.walk(base, 1, FileVisitOption.FOLLOW_LINKS)
          .filter(path -> path.getFileName().toString().contains(id))
          .findFirst();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

}
