package edu.ecm.blog.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Path("/posts")
@Component
public class PostsResource {
    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> posts() {
        return postService.find(0, 10);
    }
}
