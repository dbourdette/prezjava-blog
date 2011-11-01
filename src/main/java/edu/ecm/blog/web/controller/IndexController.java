package edu.ecm.blog.web.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

@Controller
public class IndexController {

    @Inject
    private PostService postService;

    @PostConstruct
    public void bootstrap() {
        if (postService.count() == 0) {
            for (int i = 0; i < 3; i++) {
                Post post = new Post();

                post.setTitle("un post");
                post.setSlug("un-post");
                post.setDate(new Date());
                post.setTags("tag 1,tag 2");
                post.setText("un peu de texte");

                postService.save(post);
            }
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
