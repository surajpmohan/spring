package spm.spring.rest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import spm.spring.rest.bean.ContactBean;

@Repository
public class ContactDaoImpl implements ContactDao {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public ContactDaoImpl(DataSource datasource){
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	@Override
	public ContactBean get(int id) {
		try {
			String sql = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB FROM SPRING.CONTACT WHERE ID=?";
			return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ContactBean>() {
				@Override
				public ContactBean mapRow(ResultSet rs, int arg1)
						throws SQLException {
					ContactBean contact = new ContactBean();
					contact.setDob(rs.getDate("DOB"));
					contact.setEmail(rs.getString("EMAIL"));
					contact.setFirstName(rs.getString("FIRST_NAME"));
					contact.setId(rs.getInt("ID"));
					contact.setLastName(rs.getString("LAST_NAME"));
					contact.setPhone(rs.getString("PHONE"));
					return contact;
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ContactBean> getAll() {
		String sql = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB FROM SPRING.CONTACT";
		return jdbcTemplate.query(sql, new RowMapper<ContactBean>() {

			@Override
			public ContactBean mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ContactBean contact = new ContactBean();
				contact.setDob(rs.getDate("DOB"));
				contact.setEmail(rs.getString("EMAIL"));
				contact.setFirstName(rs.getString("FIRST_NAME"));
				contact.setId(rs.getInt("ID"));
				contact.setLastName(rs.getString("LAST_NAME"));
				contact.setPhone(rs.getString("PHONE"));
				return contact;
			}
		});
	}

	@Override
	public ContactBean create(final ContactBean contact) {
		final String sql = "INSERT INTO  SPRING.CONTACT (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB)  VALUES (?,?,?,?,?)";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql, new String[]{"ID"});
				statement.setString(1, contact.getFirstName());
				statement.setString(2, contact.getLastName());
				statement.setString(3, contact.getEmail());
				statement.setString(4, contact.getPhone());
				statement.setDate(5, new java.sql.Date(contact.getDob().getTime()));
				return statement;
			}
		}, generatedKeyHolder);
		contact.setId(generatedKeyHolder.getKey().intValue());
		return contact;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM SPRING.CONTACT WHERE ID=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(ContactBean contact) {
		final String sql = "UPDATE SPRING.CONTACT SET (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB)  = (?,?,?,?,?) WHERE ID=?";
		return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(),  contact.getPhone(), contact.getDob(), contact.getId());
	}

}
