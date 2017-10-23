package org.openherbarium.module.srv.imageservice.rest.endpoint

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://127.0.0.1:8000")
    .proxy(Proxy("127.0.0.1", 7081).httpsPort(7081))
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.105 Safari/537.36 Vivaldi/1.92.917.43")

  val headers_0 = Map(
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://127.0.0.1:8000/rest/imageservice/47659/TileGroup0/0-0-0.jpg"

  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get("/rest/imageservice/47659/TileGroup0/0-0-0.jpg")
      .headers(headers_0))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}