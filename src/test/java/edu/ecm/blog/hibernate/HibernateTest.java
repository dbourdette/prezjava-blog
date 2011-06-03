package edu.ecm.blog.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.ecm.blog.domain.Author;
import edu.ecm.blog.domain.Post;


public class HibernateTest {
	@Test
	public void createSessionFactory() {
		Configuration configuration = new Configuration();
		
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:derby:target/testdb;create=true");
        configuration.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        
        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Post.class);
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
	}
}
