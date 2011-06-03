package edu.ecm.blog.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DerbyTest {
	private Connection connection;
	
	@Before
	public void init() throws ClassNotFoundException, SQLException {
		// chargement du driver
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
		// création de la connexion
		connection = DriverManager.getConnection("jdbc:derby:target/testdb;create=true");
		
		clean();
	}
	
	@After
	public void clean() throws SQLException {
		try {
			connection.createStatement().execute("drop table test ");
		} catch (SQLException e) {
			// la table n'existait pas
			if (!e.getMessage().equals("'DROP TABLE' cannot be performed on 'TEST' because it does not exist.")) {
				throw e;
			}
		}
	}
	
	@Test
	public void createTable() throws ClassNotFoundException, SQLException {
		connection.createStatement().execute("create table test (firstname char(30), lastname char(30))");
	}
	
	@Test
	public void insert() throws ClassNotFoundException, SQLException {
		createTable();
		
		PreparedStatement stmt = connection.prepareStatement("insert into test (firstname, lastname) values (?, ?)");
		
		stmt.setString(1, "Groucho");
		stmt.setString(2, "Marx");
		
		Assert.assertEquals(1, stmt.executeUpdate());
	}
}
