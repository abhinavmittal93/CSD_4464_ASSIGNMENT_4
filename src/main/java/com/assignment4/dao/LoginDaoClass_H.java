/**
 * 
 */
package com.assignment4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment4.model.LoginBean;

/**
 * @author Abhinav
 * Date - 23 Mar 2022
 * Description - This is a DAO class which handles the Database queries related to login table 
 * and transform the retrieved data and return to LoginController.
 */
@Service
public class LoginDaoClass_H {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int addUser(LoginBean loginBean) {
		// create sql statement
		String INSERT_USER_SQL = "INSERT INTO login (username, password) "
				+ "VALUES ('"+ loginBean.getUsername() +"','"+ new BCryptPasswordEncoder().encode(loginBean.getPassword()) +"');";
		
		return jdbcTemplate.update(INSERT_USER_SQL);
	}
	
	
	public LoginBean getUserDetails(String username) {
		// create sql statement
		String SELECT_USER_SQL = "SELECT * FROM login WHERE username = ?";

		return jdbcTemplate.queryForObject(SELECT_USER_SQL, new BeanPropertyRowMapper<>(LoginBean.class), new Object[] {username});
	}

}
