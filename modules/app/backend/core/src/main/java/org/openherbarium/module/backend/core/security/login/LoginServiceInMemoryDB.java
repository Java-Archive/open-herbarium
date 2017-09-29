package org.openherbarium.module.backend.core.security.login;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.openherbarium.module.api.HasLogger;
import org.rapidpm.frp.model.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LoginServiceInMemoryDB implements HasLogger {


  private static final Map<String, Pair<LocalDateTime, Integer>> FAILED_LOGINS = new ConcurrentHashMap<>();
  public static final int MAX_FAILED_LOGINS = 3;
  public static final int MINUTES_TO_WAIT = 1;
  public static final int MINUTES_TO_BE_CLEANED = 2;
  public static final int MILLISECONDS_TO_BE_CLEANED = 1_000 * 60 * MINUTES_TO_BE_CLEANED;

  public static final int MILLISECONDS_INITIAL_DELAY = 100;


  public static class FailedLoginCleaner implements HasLogger {
    private final Timer failedLoginCleanUpTimer = new Timer();

    public FailedLoginCleaner(TimerTask tasknew) {
      failedLoginCleanUpTimer.schedule(tasknew , MILLISECONDS_INITIAL_DELAY , MILLISECONDS_TO_BE_CLEANED);
    }
  }

  private static final LoginServiceInMemoryDB.FailedLoginCleaner FAILED_LOGIN_CLEANER
      = new LoginServiceInMemoryDB.FailedLoginCleaner(new TimerTask() {

    private final Logger logger = LoggerFactory.getLogger(FailedLoginCleaner.class);

    @Override
    public void run() {
      logger.debug(" start cleaning " + LocalDateTime.now());
      FAILED_LOGINS
          .keySet()
          .forEach((String key) -> {
            Pair<LocalDateTime, Integer> pair = FAILED_LOGINS.get(key);
            if (pair != null) {
              logger.debug("work on login/pair = " + key + " - " + pair);
              final Duration duration = between(pair.getT1() , LocalDateTime.now());
              long minutes = duration.toMinutes();
              if (minutes > MINUTES_TO_BE_CLEANED) {
                FAILED_LOGINS.remove(key); // start from zero
                logger.debug("  ==>  cleaned key = " + key);
              }
            }
          });
    }
  });


  public Boolean checkLogin(String login , String password) {
    if (FAILED_LOGINS.containsKey(login)) {
      Pair<LocalDateTime, Integer> pair = FAILED_LOGINS.get(login);
      LocalDateTime failedLoginDate = pair.getT1();
      Integer failedLoginCount = pair.getT2();
      if (failedLoginCount > MAX_FAILED_LOGINS) {
        logger().debug("failedLoginCount > MAX_FAILED_LOGINS " + failedLoginCount);
        final Duration duration = between(failedLoginDate , LocalDateTime.now());
        long minutes = duration.toMinutes();
        if (minutes > MINUTES_TO_WAIT) {
          logger().debug("minutes > MINUTES_TO_WAIT (remove login) " + failedLoginCount);
          FAILED_LOGINS.remove(login); // start from zero
        } else {
          logger().debug("failedLoginCount <= MAX_FAILED_LOGINS " + failedLoginCount);
          FAILED_LOGINS.compute(
              login ,
              (s , faildPair) -> new Pair<>(LocalDateTime.now() , failedLoginCount + 1));
          return false;
        }
      } else {
        logger().debug("failedLoginCount => " + login + " - " + failedLoginCount);
      }
    }


    //TODO Demo InMemory
    if (login != null && password != null && checkUserPassword(login , password)) {
      FAILED_LOGINS.remove(login);
      return true;
    } else {
      logger().debug("login failed " + login);
      FAILED_LOGINS.putIfAbsent(login , new Pair<>(LocalDateTime.now() , 0));
      FAILED_LOGINS.compute(
          login ,
          (s , oldPair) -> new Pair<>(LocalDateTime.now() , oldPair.getT2() + 1));
      return false;
    }
  }

  private boolean checkUserPassword(String login , String password) {
    if (login.equals("sven") && password.equals("sven")) return true;
    if (login.equals("admin") && password.equals("admin")) return true;
    return false;
  }
}
