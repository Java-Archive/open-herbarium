package org.openherbarium.module.srv.imageservice.rest.endpoint.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathFinderTest {

  @Test
  void test001() {
    final String id = "47659";
    final Path base = Paths.get("_data/example_images");

    final Optional<Path> path = PathFinder.find(base, id);
    assertTrue(path.isPresent());
  }

  @Test
  void test002() {
    final String id = "blub";
    final Path base = Paths.get("_data/example_images");

    final Optional<Path> path = PathFinder.find(base, id);
    assertFalse(path.isPresent());
  }

}


