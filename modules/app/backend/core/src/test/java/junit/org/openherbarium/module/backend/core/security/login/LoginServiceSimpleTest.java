package junit.org.openherbarium.module.backend.core.security.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openherbarium.module.api.security.login.LoginService;
import org.rapidpm.ddi.DI;

/**
 *
 */
public class LoginServiceSimpleTest {


  @BeforeEach
  void setUp() {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages("org.openherbarium");
    DI.activatePackages(this.getClass());
  }

  @AfterEach
  void tearDown() {
    DI.clearReflectionModel();
  }

  @Test @Disabled //TODO check NPE
  void test001() {
    Assert.assertFalse(DI.activateDI(LoginService.class).checkLogin(null, null));
  }

  @Test
  void test002() {
    Assert.assertFalse(DI.activateDI(LoginService.class).checkLogin("", ""));
  }

  @Test
  void test003() {
    Assert.assertFalse(DI.activateDI(LoginService.class).checkLogin("XX", ""));
  }

  @Test
  void test004() {
    Assert.assertFalse(DI.activateDI(LoginService.class).checkLogin("XX", "XX"));
  }

  @Test
  void test005() {
    Assert.assertTrue(DI.activateDI(LoginService.class).checkLogin("admin", "admin"));
  }
}
