package com.codenvy.demo.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/service")
public class MyEndpoint {

    public static final String VERSION = "3.12";

    @Path("/version")
    @GET
    public String version() {
        return VERSION;
    }


    @Path("/echo")
    @POST
    public String echo(String name) {
        return "hello " + name;
    }


}