package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.common.JDBCTemplate;
import springbook.user.domain.User;

public class UserDao {
	
	private static UserDao INSTANCE;
	
	private Connection c;
	
	private UserDao(Connection c) {
		this.c=c;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		String sql = "";
		sql += "INSERT INTO users(id, name, password)";
		sql += " VALUES(?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		ps.close();
		
		c.close();
		
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT * FROM users WHERE id=?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
		
	}
	
	public static synchronized UserDao getInstance() {
		if(INSTANCE == null) INSTANCE = new UserDao(JDBCTemplate.getConnection());
		return INSTANCE;
	}
	
}
