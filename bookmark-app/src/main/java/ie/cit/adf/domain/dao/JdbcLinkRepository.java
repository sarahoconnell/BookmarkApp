package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Link;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcLinkRepository implements LinkRepository {

	private JdbcTemplate jdbcTemplate;
	private linkMapper mapper = new linkMapper();

	public JdbcLinkRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Link findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, URL, DESCRIPTION, BOARDID FROM link WHERE ID=?", mapper, id);
	}

	@Override
	public List<Link> getAll() {
		return jdbcTemplate
				.query("SELECT ID, URL, DESCRIPTION, BOARDID FROM link",mapper);
	}

	@Override
	public void add(Link link) {
		jdbcTemplate.update("INSERT INTO link VALUES(?,?,?,?)", 
				link.getId(), link.getUrl(), link.getDescription(), link.getBoardId());
	}

	@Override
	public void delete(String id) {
		jdbcTemplate.update("DELETE FROM link WHERE ID=?", id);
	}

	@Override
	public void update(Link link) {
		jdbcTemplate.update("UPDATE link SET URL=?, DESCRIPTION=?, BOARDID=? WHERE ID=?",
				link.getUrl(), link.getDescription(), link.getBoardId());
	}
}

class linkMapper implements RowMapper<Link> {
	@Override
	public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
		Link link = new Link();
		link.setId(rs.getString("ID"));
		link.setUrl(rs.getString("URL"));
		link.setDescrption(rs.getString("DESCRIPTION"));
		link.setBoardId(rs.getString("BOARDID"));
		return link;
	}
}
