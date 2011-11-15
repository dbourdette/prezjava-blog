package edu.ecm.blog.ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Singleton
@Startup
public class HelloEJB {
    public String hello(String name) {
        return "Hi " + name;
    }
}
