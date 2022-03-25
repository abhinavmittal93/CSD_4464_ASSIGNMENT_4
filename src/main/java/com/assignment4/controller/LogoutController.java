/**
 * 
 */
package com.assignment4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Abhinav
 * Date - 24 Mar 2022
 * Description - This controller handles the request related to logout
 *
 */
@Controller
public class LogoutController {

	/**
	 * It logs out the user and redirect user to the login.jsp page.
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @return Login.jsp page
	 */
	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model m) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	    logoutHandler.setInvalidateHttpSession(true);
	    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login?logout=true"; // You can redirect wherever you want, but generally it's a good practice
												// to show login screen again.
	}

}
