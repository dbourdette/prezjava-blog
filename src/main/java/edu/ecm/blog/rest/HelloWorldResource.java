package edu.ecm.blog.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Path("/hi/{username}")
@Component
public class HelloWorldResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hi(@PathParam("username") String username) {
        return "Hello " + StringUtils.defaultIfEmpty(username, "unknown");
    }
}
