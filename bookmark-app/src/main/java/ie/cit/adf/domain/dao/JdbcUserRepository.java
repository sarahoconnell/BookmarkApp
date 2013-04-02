package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.User;
import ie.cit.adf.domain.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;
	private userMapper userMapper = new userMapper();

	public JdbcUserRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public User findByName(String name) {
		List<User> users =  
		jdbcTemplate.query(
				"SELECT ID, NAME, PASSWORD, TWITTERID FROM users WHERE NAME=?", userMapper, name);
		if(users.isEmpty())
			return null;
		else return users.get(0);
	}
	
	@Override
	public User findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, NAME, PASSWORD, TWITTERID FROM users WHERE ID=?", userMapper, id);
	}
	@Override
	public User get(String id) {
		User user = findById(id);
		if (user == null)
		    throw new EmptyResultDataAccessException(1);
		return user;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate
				.query("SELECT ID, NAME, PASSWORD, TWITTERID FROM users",userMapper);
	}

	@Override
	public void create(User user) {
		jdbcTemplate.update("INSERT INTO users VALUES(?,?,?,?)", 
				user.getId(), user.getName(), user.getPassword(), user.getTwitterId());
	}


	@Override
	public void createRole(UserRole role) {
		jdbcTemplate.update("INSERT INTO user_roles VALUES(?,?,?)", 
				role.getUserId(), role.getUserId(), role.getAuthority());
	}
	
	@Override
	public void delete(User user) {
		jdbcTemplate.update("DELETE FROM users WHERE ID=?", user.getId());
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
