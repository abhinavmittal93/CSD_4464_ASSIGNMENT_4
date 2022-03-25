package com.assignment4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.assignment4.model.BookBean;

/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 20 Mar 2022
 * Description - This is a DAO class which handles the Database queries related to books table 
 * and transform the retrieved data and return to BookController.
 * 
 */
@Service
public class BookDaoClass_H {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PublisherDaoClass_H publisherDao;
	
	@Autowired
	private MemberDaoClass_H memberDao;

	/**
	 * It adds a new record in books table with the details provided by the user.
	 * 
	 * @param bookBean
	 * @return the number of rows affected
	 */
	public int addBook(BookBean bookBean) {
		// create sql statement
		String INSERT_BOOK_SQL = "INSERT INTO books (title, author, price, available, pubId) " 
								+ "VALUES ('"+ bookBean.getTitle() +"','"+ bookBean.getAuthor() +"',"+ bookBean.getPrice() +",'"+ bookBean.getAvailable() +"',"+ bookBean.getPublisherBean().getPubId() +");";
		return jdbcTemplate.update(INSERT_BOOK_SQL);
	}

	/**
	 * It updates the book details by bookId with the provided details.
	 * 
	 * @param bookBean
	 * @return the number of rows affected
	 */
	public int updateBook(BookBean bookBean) {
		// create sql statement
		String UPDATE_BOOK_SQL = "UPDATE books SET title = '"+ bookBean.getTitle() +"', author = '"+ bookBean.getAuthor() +"', "
				+ "price = "+ bookBean.getPrice() +", available = '"+ bookBean.getAvailable() +"', pubId = "+ bookBean.getPublisherBean().getPubId() +" WHERE bookId = " + bookBean.getBookId();

		return jdbcTemplate.update(UPDATE_BOOK_SQL);
	}

	/**
	 * It retrieves all the records from books table and set it in the Model and
	 * return the List. Also, it retrieves the foreign key table details as well.
	 * 
	 * @return List<BookBean>
	 */
	public List<BookBean> getAllBooks() {
		
		return jdbcTemplate.query("SELECT * FROM books", new RowMapper<BookBean>() {

			@Override
			public BookBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				BookBean bookBean = new BookBean();
				bookBean.setBookId(rs.getLong("bookId"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setAvailable(rs.getString("available").charAt(0));
				bookBean.setPrice(rs.getDouble("price"));
				bookBean.setPublisherBean(publisherDao.getPublisherDetails(rs.getLong("pubId")));
				if (rs.getLong("membId") > 0) {
					bookBean.setMemberBean(memberDao.getMemberDetails(rs.getLong("membId")));
				}
				bookBean.setIssueDate(rs.getDate("issueDate"));
				bookBean.setDueDate(rs.getDate("dueDate"));
				bookBean.setReturnDate(rs.getDate("returnDate"));
				return bookBean;
			}
		});
	}

	/**
	 * It issues a book to a member by adding membId to the books table and setting
	 * it's availability to 'N'.
	 * 
	 * @param bookBean
	 * @return the number of rows affected
	 */
	public int issueBook(BookBean bookBean) {
		// create sql statement
		String ISSUE_BOOK_SQL = "UPDATE books SET membId = "+ bookBean.getMemberBean().getMembId() +", issueDate = '"+ bookBean.getIssueDate() +"', "
				+ "dueDate = '"+ bookBean.getDueDate() +"', available = 'N' WHERE bookId = " + bookBean.getBookId();

		return jdbcTemplate.update(ISSUE_BOOK_SQL);
	}

	/**
	 * It deletes the boom record from books table by bookId.
	 * 
	 * @param bookId
	 * @return the number of rows affected
	 */
	public int deleteBook(Long bookId) {
		// create sql statement
		String DELETE_BOOK_SQL = "DELETE from books WHERE bookId = " + bookId;

		return jdbcTemplate.update(DELETE_BOOK_SQL);
	}
	
	/**
	 * It gets the List of books which are currently borrowed or were borrowed in the past.
	 * 
	 * @return the list of books borrowed
	 * @throws ClassNotFoundException
	 */
	public List<BookBean> getIssuedBooks() throws ClassNotFoundException {

		// create sql statement
		String SELECT_BOOKS_SQL = "SELECT * FROM books where (returnDate IS NULL AND available = 'N') "
				+ "OR (returnDate IS NOT NULL AND available = 'Y')";
		
		
		return jdbcTemplate.query(SELECT_BOOKS_SQL, new RowMapper<BookBean>() {

			@Override
			public BookBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				BookBean bookBean = new BookBean();
				bookBean.setBookId(rs.getLong("bookId"));
				bookBean.setTitle(rs.getString("title"));
				bookBean.setAuthor(rs.getString("author"));
				bookBean.setAvailable(rs.getString("available").charAt(0));
				bookBean.setPrice(rs.getDouble("price"));
				bookBean.setPublisherBean(publisherDao.getPublisherDetails(rs.getLong("pubId")));
				if (rs.getLong("membId") > 0) {
					bookBean.setMemberBean(memberDao.getMemberDetails(rs.getLong("membId")));
				}
				bookBean.setIssueDate(rs.getDate("issueDate"));
				bookBean.setDueDate(rs.getDate("dueDate"));
				bookBean.setReturnDate(rs.getDate("returnDate"));
				return bookBean;
			}
		});
	}

}
