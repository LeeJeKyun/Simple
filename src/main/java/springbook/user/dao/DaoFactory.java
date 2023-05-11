package springbook.user.dao;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springbook.user.common.JDBCTemplate;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao getUserDao() {
		UserDao userDao = UserDao.getInstance();
		
		return userDao;
	}
	
	@Bean
	public Connection connectionMaker() {
		
		return JDBCTemplate.getConnection();
	}
	
}
