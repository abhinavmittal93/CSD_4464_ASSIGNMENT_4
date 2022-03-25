/**
 * 
 */
package com.assignment4.model;

import java.sql.Date;

/**
 * @author Abhinav, Samridhi, Aarti 
 * Date - 20 Mar 2022 
 * Description - Model class BookBean for table Books with bookId, title, author, price,
 *  available, PublisherBean, MemberBean, issueDate, dueDate, returnDate.
 *
 */
public class BookBean {

	private Long bookId;

	private String title;

	private String author;

	private double price;

	private char available;

	private PublisherBean publisherBean;

	private MemberBean memberBean;

	private Date issueDate;

	private Date dueDate;

	private Date returnDate;

	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the available
	 */
	public char getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(char available) {
		this.available = available;
	}

	/**
	 * @return the publisherBean
	 */
	public PublisherBean getPublisherBean() {
		return publisherBean;
	}

	/**
	 * @param publisherBean the publisherBean to set
	 */
	public void setPublisherBean(PublisherBean publisherBean) {
		this.publisherBean = publisherBean;
	}

	/**
	 * @return the memberBean
	 */
	public MemberBean getMemberBean() {
		return memberBean;
	}

	/**
	 * @param memberBean the memberBean to set
	 */
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
