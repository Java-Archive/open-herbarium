package org.openherbarium.module.srv.imageservice.rest.endpoint;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("imageservice")
public class ImageServiceRestEndpoint {

  @Inject
  private ImageService imageService;

  @GET
  @Path("/{imageid}/properties")
  @Produces(MediaType.TEXT_XML)
  public String getImageProperties(@PathParam("imageid") String imageid) {
    return imageService.getImageProperties(imageid)
        .orElse("");
  }

  @GET
  @Path("/{imageid}/{tilegroup}/{image}")
  public Response getImage(@QueryParam("imageid") String imageid,
                           @QueryParam("tilegroup") String tilegroup,
                           @QueryParam("image") String image) {
    final byte[] bytes = imageService.getImage(imageid, tilegroup, image);

    return Response.ok(bytes, MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
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
