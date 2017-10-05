package org.openherbarium.module.srv.imageservice.rest.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("imageservice")
public class ImageServiceRestEndpoint {

  @Inject
  private ImageService imageService;

  @GET
  @Path("/{imageid}/{tilegroup}/{image}")
  public Response getImage(@QueryParam("imageid") String imageid,
                           @QueryParam("tilegroup") String tilegroup,
                           @QueryParam("image") String image) {




  }

/*
  @GET
  @Path("/image/{id}")
  public Response getImage(@PathParam("id") String id) {
    // do something
    return Response.ok().build();
  }

  @POST
  @Path("/image/{id}")
  public Response updateImage(@PathParam("id") String id) {
    // do something
    return Response.ok().build();
  }

  @DELETE
  @Path("/image/{id}")
  public Response deleteImage(@PathParam("id") String id) {
    // do something
    return Response.ok().build();
  }
*/
}
