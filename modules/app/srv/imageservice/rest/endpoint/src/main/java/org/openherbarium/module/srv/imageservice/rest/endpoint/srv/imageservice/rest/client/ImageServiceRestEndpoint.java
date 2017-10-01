package org.openherbarium.module.srv.imageservice.rest.endpoint.srv.imageservice.rest.client;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("imageservice")
public class ImageServiceRestEndpoint {

  // TODO
  // getImage()
  // updateImage(String id)
  // removeImage(String id)

  @GET
  @Path("/image/{id}")
  public Response getImage(@PathParam("id") String id){
    // do something
    return Response.ok().build();
  }

  @POST
  @Path("/image/{id}")
  public Response updateImage(@PathParam("id") String id){
    // do something
    return Response.ok().build();
  }

  @DELETE
  @Path("/image/{id}")
  public Response deleteImage(@PathParam("id") String id){
    // do something
    return Response.ok().build();
  }

}
