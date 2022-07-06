package org.abratuhi;

import io.vertx.core.http.HttpServerRequest;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@JBossLog
@Path("/hello")
public class GreetingResource {

    @RestClient
    AdditionalRestClient additionalRestClient;

    @Context
    HttpServerRequest httpServerRequest;

    @GET
    @Path("/simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String simpleHello() {
        doLogAuthorization();
        return "Hello from RESTEasy Reactive.";
    }

    @GET
    @Path("/complex")
    @Produces(MediaType.TEXT_PLAIN)
    public String complexHelloWithAdditional() {
        doLogAuthorization();
        return "Hello from RESTEasy Reactive." + additionalRestClient.getAdditionalString();
    }

    private void doLogAuthorization() {
        String authorization = httpServerRequest.getHeader("Authorization");
        if (authorization != null) {
            log.infov("Authorization found: {0}", authorization);
        } else {
            log.error("Authorization not found");
        }
    }
}