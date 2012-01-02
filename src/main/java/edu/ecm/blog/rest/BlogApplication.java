package edu.ecm.blog.rest;

import com.sun.jersey.api.core.PackagesResourceConfig;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class BlogApplication extends PackagesResourceConfig {
    public BlogApplication() {
        super("edu.ecm.blog.rest");
    }
}
