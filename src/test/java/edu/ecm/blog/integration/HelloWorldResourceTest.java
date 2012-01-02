package edu.ecm.blog.integration;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class HelloWorldResourceTest {
    @Test
    public void hi() {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/rest/hi/jax-rs");

        Assert.assertEquals("Hello jax-rs", resource.get(String.class));
    }
}
