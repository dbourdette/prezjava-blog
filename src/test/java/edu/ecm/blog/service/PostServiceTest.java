package edu.ecm.blog.service;

import java.util.Date;
import javax.inject.Inject;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import edu.ecm.blog.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PostServiceTest {

    @Inject
    private SessionFactory sessionFactory;

    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        session.createQuery("delete from Post").executeUpdate();

        session.close();

        sessionFactory.close();
    }

    @Test
    public void save() {
        PostService postService = new PostService();
        postService.setSessionFactory(sessionFactory);

        Post post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);
    }

    @Test
    public void delete() {
        PostService postService = new PostService();
        postService.setSessionFactory(sessionFactory);

        Post post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);

        postService.delete(post.getId());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from Post").list().size());

        session.close();
    }

    @Test
    public void find() {
        PostService postService = new PostService();
        postService.setSessionFactory(sessionFactory);

        Post post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);

        post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);

        Assert.assertEquals(2, postService.find(0, 10).size());
        Assert.assertEquals(1, postService.find(0, 1).size());
    }

    @Test
    public void count() {
        PostService postService = new PostService();
        postService.setSessionFactory(sessionFactory);

        Post post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);

        post = new Post();
        post.setTitle("un post");
        post.setDate(new Date());

        postService.save(post);

        Assert.assertEquals(2, postService.count());
    }
}
