package edu.ecm.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ecm.blog.domain.Post;

@Service
public class PostServiceImpl implements PostService {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Post post) {
        Session session = sessionFactory.getCurrentSession();

        session.save(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        session.createQuery("delete Post where id = :id").setLong("id", id).executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Post> find(int pageIndex, int pageSize) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Post.class);

        criteria.setFirstResult(pageIndex * pageSize);
        criteria.setMaxResults(pageSize);

        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Post findBySlug(String slug) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Post.class);

        criteria.add(Restrictions.eq("slug", slug));

        List<Post> posts = criteria.list();

        if (posts.size() == 0) {
            return null;
        }

        return posts.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public int count() {
        Session session = sessionFactory.openSession();

        return ((Long) session.createQuery("select count(*) from Post").uniqueResult()).intValue();
    }
}
