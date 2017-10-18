package org.openherbarium.module.srv.imageservice.rest.endpoint.rest;

import org.openherbarium.module.srv.imageservice.rest.endpoint.api.ImageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/imageservice")
public class ImageServiceRestEndpoint {

  @Inject
  private ImageService imageService;

  @GET
  @Path("{imageid}/properties")
  @Produces(MediaType.TEXT_XML)
  public Response getImageProperties(@PathParam("imageid") String imageid) {
    final Optional<String> imageProperties = imageService.getImageProperties(imageid);
    if (imageProperties.isPresent()) {
      return Response.ok(imageProperties.get()).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity("").build();
    }
  }

  @GET
  @Path("{imageid}/{tilegroup}/{image}")
  public Response getImage(@PathParam("imageid") String imageid,
                           @PathParam("tilegroup") String tilegroup,
                           @PathParam("image") String image) {
    // TODO no default image, just response with or without image
    final byte[] bytes = imageService.getImage(imageid, tilegroup, image);

    return Response.ok(bytes, MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
  }

}
