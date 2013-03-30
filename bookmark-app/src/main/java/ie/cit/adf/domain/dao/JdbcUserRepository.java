package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;
	private userMapper userMapper = new userMapper();

	public JdbcUserRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, NAME, PASSWORD, TWITTERID FROM users WHERE ID=?", userMapper, id);
	}

	@Override
	public List<User> getAll() {
		return jdbcTemplate
				.query("SELECT ID, NAME, PASSWORD, TWITTERID FROM users",userMapper);
	}

	@Override
	public void add(User user) {
		jdbcTemplate.update("INSERT INTO users VALUES(?,?,?,?)", 
				user.getId(), user.getName(), user.getPassword(), user.getTwitterId());
	}

	@Override
	public void delete(String id) {
		jdbcTemplate.update("DELETE FROM users WHERE ID=?", id);
	}

	@Override
	public void update(User user) {
		jdbcTemplate.update("UPDATE users SET NAME=?, PASSWORD=?, TWITTERID=? WHERE ID=?",
				 user.getName(), user.getPassword(), user.getTwitterId());
	}
}

class userMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getString("ID"));
		user.setName(rs.getString("NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setTwitterId(rs.getString("TWITTERID"));
		return user;
	}
}
