package com.assignment4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.assignment4.model.MemberBean;

/**
 * @author Abhinav, Aarti, Samridhi
 * Date - 20 Mar 2022
 * Description - This is a DAO class which handles the Database queries related to member table 
 * and transform the retrieved data and return to MemberController.
 * 
 */
@Service
public class MemberDaoClass_H {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * It adds a new record in member table with the details provided by the user.
	 * 
	 * @param member
	 * @return the number of rows affected
	 */
	public int registerMember(MemberBean member) {
		// create sql statement
		String INSERT_MEMBER_SQL = "INSERT INTO member (name, address, membType, membDate, expiryDate) "
				+ "VALUES ('"+ member.getName() +"','"+ member.getAddress() +"','"+ member.getMembType() +"','"+ member.getMembDate() +"','"+ member.getExpiryDate() +"');";
		
		return jdbcTemplate.update(INSERT_MEMBER_SQL);
	}

	/**
	 * It updates the member details by membId with the provided details.
	 * 
	 * @param member
	 * @return the number of rows affected
	 */
	public int updateMember(MemberBean member) throws ClassNotFoundException {

		// create sql statement
		String UPDATE_MEMBER_SQL = "UPDATE member SET name = '"+ member.getName() +"', address = '"+ member.getAddress() +"', membType = '"+ member.getMembType() +"' WHERE membId = " + member.getMembId();

		return jdbcTemplate.update(UPDATE_MEMBER_SQL);
	}

	/**
	 * It retrieves the member details by membId, from member table and set it in the Model and return it.
	 * 
	 * @param membId
	 * @return MemberBean
	 */
	public MemberBean getMemberDetails(Long membId) {
		// create sql statement
		String SELECT_MEMBER_SQL = "SELECT * FROM member WHERE membId = ?";

		return jdbcTemplate.queryForObject(SELECT_MEMBER_SQL, new BeanPropertyRowMapper<>(MemberBean.class), new Object[] {membId});
	}
	
	/**
	 * It retrieves all the records from member table and set it in the Model and return the List.
	 * 
	 * @return List<MemberBean>
	 */
	public List<MemberBean> getAllMembers() {
		
		
		return jdbcTemplate.query("SELECT * FROM member", new RowMapper<MemberBean>() {

			@Override
			public MemberBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberBean memberBean = new MemberBean();
				memberBean.setMembId(rs.getLong("membId"));
				memberBean.setName(rs.getString("name"));
				memberBean.setAddress(rs.getString("address"));
				memberBean.setMembType(rs.getString("membType"));
				memberBean.setMembDate(rs.getDate("membDate"));
				memberBean.setExpiryDate(rs.getDate("expiryDate"));
				return memberBean;
			}
		});
	}

}
