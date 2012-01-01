package edu.ecm.blog.webservice;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import edu.ecm.blog.service.PostService;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PostWebService extends SpringBeanAutowiringSupport {
    @Inject
    private PostService postService;

    public String hi(String name) {
        return "Hello " + StringUtils.defaultIfEmpty(name, "unknown");
    }
    
    public Posts latestPosts() {
       return new Posts(postService.find(0, 10));
    }
}
