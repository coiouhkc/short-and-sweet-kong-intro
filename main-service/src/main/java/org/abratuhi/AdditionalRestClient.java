package org.abratuhi;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/additional")
@RegisterRestClient(configKey = "additional-api")
//@RegisterClientHeaders
public interface AdditionalRestClient {
    @GET
    String getAdditionalString();
}
