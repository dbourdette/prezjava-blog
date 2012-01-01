package edu.ecm.blog.integration;

import org.junit.Assert;
import org.junit.Test;

import edu.ecm.blog.integration.generated.PostWebService;
import edu.ecm.blog.integration.generated.PostWebServiceService;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class PostWebServiceTest {
    @Test
    public void hi() {
        PostWebServiceService postWebServiceService = new PostWebServiceService();
        PostWebService postWebService = postWebServiceService.getPostWebServicePort();

        Assert.assertEquals("Hello jax-ws", postWebService.hi("jax-ws"));
    }

    @Test
    public void latestPosts() {
        PostWebServiceService postWebServiceService = new PostWebServiceService();
        PostWebService postWebService = postWebServiceService.getPostWebServicePort();

        Assert.assertTrue(postWebService.latestPosts().getPosts().size() > 0);
        Assert.assertTrue(postWebService.latestPosts().getPosts().get(0).getTitle().length() > 0);
    }
}
