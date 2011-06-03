package edu.ecm.blog.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		drop();
	}
	
	@After
	public void clean() throws SQLException {
		drop();
		
		connection.close();
	}
	
	@Test
	public void createTable() throws ClassNotFoundException, SQLException {
		connection.createStatement().execute("create table test (firstname varchar(30), lastname varchar(30))");
	}
	
	public void toto() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DriverManager.getConnection("...");
			
			stmt = connection.prepareStatement("...");
			
			stmt.execute();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Test
	public void insert() throws ClassNotFoundException, SQLException {
		createTable();
		
		PreparedStatement stmt = connection.prepareStatement("insert into test (firstname, lastname) values (?, ?)");
		
		stmt.setString(1, "Groucho");
		stmt.setString(2, "Marx");
		
		Assert.assertEquals(1, stmt.executeUpdate());
		
		stmt.close();
	}
	
	@Test
	public void select() throws ClassNotFoundException, SQLException {
		insert();
		
		PreparedStatement stmt = connection.prepareStatement("select firstname from test where lastname = ?");
		
		stmt.setString(1, "Marx");
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		Assert.assertEquals("Groucho", rs.getString("firstname"));
		
		stmt.close();
	}
	
	@Test
	public void delete() throws ClassNotFoundException, SQLException {
		insert();
		
		PreparedStatement stmt = connection.prepareStatement("delete from test where lastname = ?");
		
		stmt.setString(1, "Marx");
		
		Assert.assertEquals(1, stmt.executeUpdate());
		
		stmt.close();
	}
	
	public void drop() throws SQLException {
		try {
			Statement stmt = connection.createStatement();
			
			stmt.execute("drop table test ");
			
			stmt.close();
		} catch (SQLException e) {
			// la table n'existait pas
			if (!e.getMessage().equals("'DROP TABLE' cannot be performed on 'TEST' because it does not exist.")) {
				throw e;
			}
		}
	}
}
