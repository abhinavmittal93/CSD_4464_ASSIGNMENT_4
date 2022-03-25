/**
 * 
 */
package com.assignment4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment4.dao.LoginDaoClass_H;
import com.assignment4.model.LoginBean;

/**
 * @author Abhinav
 * Date - 24 Mar 2022
 * Description - It handles all the request related to login, register
 *
 */

@RequestMapping("/")
@Controller
public class LoginController {

	@Autowired
	private LoginDaoClass_H loginDao;

	/**
	 * 
	 * It gets the Login.jsp page
	 * 
	 * @param m
	 * @param error
	 * @param logout
	 * @param request
	 * @return Login.jsp page if the user is not logged in, otherwise index.jsp
	 */
	@RequestMapping("/login")
	public String getLoginPage(Model m, @RequestParam(name = "error", required = false) boolean error,
			@RequestParam(name = "logout", required = false) boolean logout, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// checking if the user is already logged in
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/index";
		}

		// checking if there's any error with username and password
		if (error) {
			HttpSession session = request.getSession(false);
			String errorMessage = null;
			if (session != null) {
				AuthenticationException ex = (AuthenticationException) session
						.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
				if (ex != null) {
					errorMessage = ex.getMessage();
				}
			}
			m.addAttribute("errorMessage", errorMessage);
		}

		// checking if the user is logged out
		if (logout) {
			m.addAttribute("message", "You have been logged out successfully.");
		}

		m.addAttribute("login", new LoginBean());
		return "Login";
	}

	/**
	 * Redirects user to index.jsp page when user is successfully logged in.
	 * 
	 * @param loginBean
	 * @param m
	 * @return index.jsp
	 */
	@PostMapping("/login")
	public String login(LoginBean loginBean, Model m) {
		return "redirect:/index";
	}

	/**
	 * 
	 * It gets the Register.jsp page, on which user can register himself
	 * 
	 * @param m
	 * @return Register.jsp page
	 */
	@RequestMapping("/register")
	public String getRegistrationPage(Model m) {
		m.addAttribute("login", new LoginBean());
		return "Register";
	}

	/**
	 * It saves the user details while registering if the username does not exists already.
	 * 
	 * @param loginBean
	 * @param br
	 * @param m
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/register")
	public String register(@ModelAttribute("login") @Valid LoginBean loginBean, BindingResult br, Model m) {
		if (br.hasErrors()) {
			return "Register";
		}
		
		if(loginDao.getUserDetails(loginBean.getUsername()) != null) {
			m.addAttribute("message", "User with username: " + loginBean.getUsername() + " exists.");
			return "Response";
		}
		
		loginDao.addUser(loginBean);
		m.addAttribute("message", "User has been registered successfully.");
		return "Response";
	}
	
	/**
	 * It gets the index.jsp page
	 * 
	 * @param m
	 * @return index.jsp page
	 */
	@RequestMapping("/index")
	public String getIndexPage(Model m) {
		boolean isLoggedIn = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			isLoggedIn = true;
		}
		m.addAttribute("isLoggedIn", isLoggedIn);
		return "../../index";
	}

}
