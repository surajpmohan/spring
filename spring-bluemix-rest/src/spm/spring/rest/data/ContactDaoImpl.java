package spm.spring.rest.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		String sql = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, DOB FROM SPRING.CONTACT";
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
	}

	@Override
	public List<ContactBean> getAll() {
		return null;
	}

	@Override
	public ContactBean create(ContactBean contact) {
		return null;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int update(ContactBean contact) {
		return 0;
	}

}
