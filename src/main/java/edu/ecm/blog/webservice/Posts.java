package edu.ecm.blog.webservice;

import java.util.List;

import edu.ecm.blog.domain.Post;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class Posts {
    private List<Post> posts;

    public Posts() {
    }

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
