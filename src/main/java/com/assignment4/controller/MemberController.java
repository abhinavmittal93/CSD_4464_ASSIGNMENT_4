/**
 * 
 */
package com.assignment4.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment4.dao.MemberDaoClass_H;
import com.assignment4.model.MemberBean;

/**
 * @author Aarti, Samridhi
 * Date - 23 Mar 2022
 * Description - This controller handles the request related to register and update of members.
 * 
 */

@RequestMapping("/member")
@Controller
public class MemberController {

	@Autowired
	private MemberDaoClass_H memberDao;

	/**
	 * It gets the Member.jsp page
	 * 
	 * @param m
	 * @return Member.jsp page
	 */
	@RequestMapping("")
	public String getAddMemberPage(Model m) {
		m.addAttribute("member", new MemberBean());
		return "Member";
	}

	/**
	 * It adds or updated the member with the details entered by the user on Member.jsp page.
	 * 
	 * @param memberBean
	 * @param br
	 * @param action
	 * @param m
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/save")
	public String addOrUpdateMember(@ModelAttribute("member") @Valid MemberBean memberBean,	BindingResult br, 
			String action, Model m) {
		if (br.hasErrors()) {
			return "Member";
		} 
		
		String message = null;
		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				if (memberBean.getMembId() == null || memberBean.getMembId() < 1) {
					message = "Please enter the Member ID to update!!!";
				} else {
					memberDao.updateMember(memberBean);
					message = "Member is updated successfully.";
				}
			} else {

				java.util.Date date = new java.util.Date();
				memberBean.setMembDate(new Date(date.getTime()));

				Calendar now = Calendar.getInstance(); // creates the Calendar object of the current time
				now.add(Calendar.MONTH, 6); // add 6 months to current date
				memberBean.setExpiryDate(new Date((now.getTime()).getTime())); // creates the sql Date of the above
																				// created object

				memberDao.registerMember(memberBean);
				message = "Member is added successfully.";
			}
		} catch (Exception e) {
			message = "An error occurred. Please try again!!!";
			System.out.println("An exception occurred in addOrUpdateMember(), " + e);
		}
		m.addAttribute("message", message);
		return "Response";
	}

}
