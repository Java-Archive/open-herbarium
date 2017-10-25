package org.openherbarium.module.srv.imageservice.rest.endpoint

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:7081")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36 Vivaldi/1.91.867.42")

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  val scnProperties = scenario("Properties")
    .exec(http("Properties")
      .get("/rest/imageservice/47659/properties")
      .headers(headers_0))

  val scnImage = scenario("Image")
    .exec(http("Image")
      .get("/rest/imageservice/47659/TileGroup0/0-0-0.jpg")
      .headers(headers_0))

  setUp(
    scnProperties.inject(
      atOnceUsers(100),
      constantUsersPerSec(30) during 15 randomized
    ),
    scnImage.inject(
      heavisideUsers(500) over 30
    ))
    .protocols(httpProtocol)
}