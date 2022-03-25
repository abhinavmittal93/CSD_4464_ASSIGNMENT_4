/**
 * 
 */
package com.assignment4.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment4.dao.BookDaoClass_H;
import com.assignment4.dao.MemberDaoClass_H;
import com.assignment4.dao.PublisherDaoClass_H;
import com.assignment4.model.BookBean;
import com.assignment4.model.MemberBean;
import com.assignment4.model.PublisherBean;

/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 23 Mar 2022
 * Description - This controller handles the request related to add, update, issue and delete books.
 * 
 */

@RequestMapping("/book")
@Controller
public class BookController {
	
	@Autowired
	private BookDaoClass_H bookDao;
	
	@Autowired
	private PublisherDaoClass_H publisherDaoClass_H;
	
	@Autowired
	private MemberDaoClass_H memberDaoClass_H;
	
	/**
	 * It gets the Book.jsp Page and populate all the lists sent to the page.
	 * 
	 * @param m
	 * @return Book.jsp Page
	 */
	@RequestMapping("")
	public String getBooksPage(Model m) {
		List<BookBean> books = bookDao.getAllBooks();
		List<MemberBean> members = memberDaoClass_H.getAllMembers();
		List<PublisherBean> publishers = publisherDaoClass_H.getAllPublishers();
		
		m.addAttribute("books", books);
		m.addAttribute("members", members);
		m.addAttribute("publishers", publishers);
		
		return "Book";
	}
	
	/**
	 * 
	 * It adds or updates a book, with the details entered by the user on Book.jsp page
	 * 
	 * @param bookBean
	 * @param action
	 * @param m
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/save")
	public String addOrUpdateBook(BookBean bookBean, String action, Model m) {
		String message = null;
		try {
			if ("UPDATE".equalsIgnoreCase(action)) {
				if (bookBean.getBookId() == null || bookBean.getBookId() < 1) {
					message = "Please enter the Book ID to update!!!";
				} else {
					bookDao.updateBook(bookBean);
					message = "Book is updated successfully.";
				}
			} else {
				bookDao.addBook(bookBean);
				message = "Book is added successfully.";
			}
		} catch (Exception e) {
			message = "An error occurred. Please try again!!!";
			System.out.println("An exception occurred in addOrUpdateBook(), " + e);
			e.printStackTrace();
		}
		m.addAttribute("message", message);
		return "Response";
	}
	
	/**
	 * 
	 * It deletes the book selected by the user on the Book.jsp page
	 * 
	 * @param bookBean
	 * @param m
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/delete")
	public String deleteBook(BookBean bookBean, Model m) {
		String message = "Book is deleted successfully.";
		try {
			bookDao.deleteBook(bookBean.getBookId());
		} catch (Exception e) {
			message = "An error occurred. Please try again!!!";
			System.out.println("An exception occurred in deleteBook(), " + e);
			e.printStackTrace();
		}
		m.addAttribute("message", message);
		return "Response";
	}
	
	/**
	 * 
	 * It issues a book to a member
	 * 
	 * @param bookBean
	 * @param action
	 * @param m
	 * @param pubId
	 * @return Response.jsp page with proper response if the operation is success or failed.
	 */
	@PostMapping("/issue")
	public String issueBook(BookBean bookBean, String action, Model m, String pubId) {
		String message = "Book is issued successfully.";
		try {
			java.util.Date date = new java.util.Date();
			Date issueDate = new Date(date.getTime());
			
			Calendar now = Calendar.getInstance(); // creates the Calendar object of the current time
			now.add(Calendar.MONTH, 1); // add 1 month to current date
			Date dueDate = new Date((now.getTime()).getTime());// creates the sql Date of the above created object
			
			bookBean.setIssueDate(issueDate);
			bookBean.setDueDate(dueDate);
			bookDao.issueBook(bookBean);
		} catch (Exception e) {
			message = "An error occurred. Please try again!!!";
			System.out.println("An exception occurred in issueBook(), " + e);
			e.printStackTrace();
		}
		m.addAttribute("message", message);
		return "Response";
	}
	
	/**
	 * It gets the Borrow.jsp page and shows all the issued/borrowed book details along with the member details
	 * 
	 * @param m
	 * @return Borrow.jsp page
	 */
	@RequestMapping("/borrowed")
	public String getBorrowedBooksList(Model m) {
		List<BookBean> books = null;
		try {
			books = bookDao.getIssuedBooks();
		} catch (Exception e) {
			System.out.println("Exception occurred while getting borrowed books: " + e);
			e.printStackTrace();
			m.addAttribute("message", "Error occurred. Please try again!!!");
			return "Response";
		}
		m.addAttribute("books", books);
		return "Borrow";
	}

}
