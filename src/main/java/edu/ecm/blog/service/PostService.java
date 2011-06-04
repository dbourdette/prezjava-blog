package edu.ecm.blog.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.ecm.blog.domain.Post;

public class PostService {
	private SessionFactory sessionFactory;
	
	public void save(Post post) {
		Session session = sessionFactory.openSession();
		
		try {
			session.save(post);
		} finally {
			session.close();
		}
	}
	
	public void delete(Long id) {
		Session session = sessionFactory.openSession();
		
		try {
			session.createQuery("delete Post where id = :id").setLong("id", id).executeUpdate();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> find(int pageIndex, int pageSize) {
		Session session = sessionFactory.openSession();
		
		try {
			Criteria criteria = session.createCriteria(Post.class);
			
			criteria.setFirstResult(pageIndex * pageSize);
			criteria.setMaxResults(pageSize);
			
			return criteria.list();
		} finally {
			session.close();
		}
	}
	
	public int count() {
		Session session = sessionFactory.openSession();
		
		try {
			return ((Long) session.createQuery("select count(*) from Post").uniqueResult()).intValue();
		} finally {
			session.close();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
