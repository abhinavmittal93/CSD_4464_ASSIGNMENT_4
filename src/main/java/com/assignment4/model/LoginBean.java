/**
 * 
 */
package com.assignment4.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Abhinav
 * Date - 20 Mar 2022
 * Description - Model class LoginBean for table login with username and password.
 *
 */
public class LoginBean {
	
	@NotNull
	@Size(min=1, message = "required")
	@Size(max=45, message = "Maximum characters 45")
	private String username;
	
	@NotNull
	@Size(min=1, message = "required")
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
