package com.assignment4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.assignment4.model.PublisherBean;

/**
 * @author Abhinav, Navdeep, Syed Ahmed 
 * Date - 20 Mar 2022 
 * Description - This is a DAO class which handles the Database queries related to publisher
 *   table and transform the retrieved data and return to PublisherController.
 */
@Service
public class PublisherDaoClass_H {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * It adds a new record in publisher table with the details provided by the
	 * user.
	 * 
	 * @param publisherBean
	 * @return the number of rows affected
	 */
	public int registerPublisher(PublisherBean publisherBean) throws ClassNotFoundException {
		// create sql statement
		String INSERT_PUBLISHER_SQL = "INSERT INTO publisher (name, address) VALUES ('" + publisherBean.getName() + "', '" + publisherBean.getAddress() + "');";

		return jdbcTemplate.update(INSERT_PUBLISHER_SQL);

	}

	/**
	 * It updates the publisher details by pubId with the provided details.
	 * 
	 * @param publisherBean
	 * @return the number of rows affected
	 */
	public int updatePublisher(PublisherBean publisherBean) throws ClassNotFoundException {
		// create sql statement
		String UPDATE_PUBLISHER_SQL = "UPDATE publisher SET name = '" + publisherBean.getName() + "', address = '" + publisherBean.getAddress() + "' WHERE pubId = " + publisherBean.getPubId();

		return jdbcTemplate.update(UPDATE_PUBLISHER_SQL);

	}

	/**
	 * 
	 * It retrieves the publisher details by pubId, from publisher table and set it
	 * in the Model and return it.
	 * 
	 * @param pubId
	 * @return PublisherBean
	 */
	public PublisherBean getPublisherDetails(Long pubId) {

		// create sql statement
		String SELECT_PUBLISHER_SQL = "SELECT * FROM publisher WHERE pubId = ?";
		
		return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, new BeanPropertyRowMapper<>(PublisherBean.class),  new Object[] {pubId});
	}

	/**
	 * 
	 * It retrieves all the records from publisher table and set it in the Model and
	 * return the List.
	 * 
	 * @return List<PublisherBean>
	 */
	public List<PublisherBean> getAllPublishers() {

		return jdbcTemplate.query("SELECT * FROM publisher", new RowMapper<PublisherBean>() {

			@Override
			public PublisherBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				PublisherBean publisherBean = new PublisherBean();
				publisherBean.setPubId(rs.getLong("pubId"));
				publisherBean.setName(rs.getString("name"));
				publisherBean.setAddress(rs.getString("address"));
				return publisherBean;
			}
		});
	}

}
