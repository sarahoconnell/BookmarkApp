package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Bookmark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcBookmarkRepository implements BookmarkRepository {

	private JdbcTemplate jdbcTemplate;
	private bookmarkMapper bookmarkMapper = new bookmarkMapper();

	public JdbcBookmarkRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Bookmark findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, TEXT, DONE FROM bookmark WHERE ID=?", bookmarkMapper, id);
	}

	@Override
	public List<Bookmark> getAll() {
		return jdbcTemplate
				.query("SELECT ID, TEXT, DONE FROM bookmark", bookmarkMapper);
	}

	@Override
	public void add(Bookmark bookmark) {
		jdbcTemplate.update("INSERT INTO bookmark VALUES(?,?,?)", bookmark.getId(),
				bookmark.getText(), bookmark.isDone());
	}

	@Override
	public void delete(String bookmarkId) {
		jdbcTemplate.update("DELETE FROM bookmark WHERE ID=?", bookmarkId);
	}

	@Override
	public void update(Bookmark bookmark) {
		jdbcTemplate.update("UPDATE bookmark SET TEXT=?, DONE=? WHERE ID=?",
				bookmark.getText(), bookmark.isDone(), bookmark.getId());
	}
}

class bookmarkMapper implements RowMapper<Bookmark> {
	@Override
	public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bookmark bookmark = new Bookmark();
		bookmark.setId(rs.getString("ID"));
		bookmark.setText(rs.getString("TEXT"));
		bookmark.setDone(rs.getBoolean("DONE"));
		return bookmark;
	}
}
