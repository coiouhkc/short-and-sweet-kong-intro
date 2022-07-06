package org.abratuhi;

import io.vertx.core.http.HttpServerRequest;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@JBossLog
@Path("/additional")
public class GreetingResource {

    @Context
    HttpServerRequest httpServerRequest;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String authorization = httpServerRequest.getHeader("Authorization");
        if (authorization != null) {
            log.infov("Authorization found: {0}", authorization);
        } else {
            log.error("Authorization not found");
        }

        return "Some additional string.";
    }
}