/**
 * 
 */
package com.assignment4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment4.dao.PublisherDaoClass_H;
import com.assignment4.model.PublisherBean;

/**
 * @author Navdeep, Syed Ahmed
 * Date - 23 Mar 2022
 * Description - This controller handles the request related to register and update of publishers.
 *
 */

@RequestMapping("/publisher")
@Controller
public class PublisherController {
	
	@Autowired
	private PublisherDaoClass_H publisherDao;
	
	/**
	 * It gets the Publisher.jsp page
	 * 
	 * @return Publisher.jsp page
	 */
	@RequestMapping("")
	public String getAddPublisherPage() {
		return "Publisher";
	}
	
	/**
	 * It adds or updates the publisher with the details entered by the user on Publisher.jsp page.
	 * 
	 * @param publisherBean
	 * @param action
	 * @param m
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/save")
	public String addOrUpdatePublisher(PublisherBean publisherBean, String action, Model m) {
		String message = null;
		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				if (publisherBean.getPubId() == null || publisherBean.getPubId() < 1) {
					message = "Please enter the Publisher ID to update!!!";
				} else {
					publisherDao.updatePublisher(publisherBean);
					message = "Publisher is updated successfully.";
				}
			} else {
				publisherDao.registerPublisher(publisherBean);
				message = "Publisher is added successfully.";
			}
		} catch (Exception e) {
			message = "An error occurred. Please try again!!!";
			System.out.println("An exception occurred in addOrUpdatePublisher(), " + e);
		}
		m.addAttribute("message", message);
		return "Response";
	}

}
