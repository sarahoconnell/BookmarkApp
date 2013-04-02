package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcBoardRepository implements BoardRepository {

	private JdbcTemplate jdbcTemplate;
	private boardMapper mapper = new boardMapper();

	public JdbcBoardRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Board get(String id) {
		Board board = findById(id);
		if (board == null)
		    throw new EmptyResultDataAccessException(1);
		return board;
	}

    public Board findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, NAME, DESCRIPTION FROM board WHERE ID=?", mapper, id);
    }

    
	@Override
	public List<Board> findAll() {
		return jdbcTemplate
				.query("SELECT ID, NAME, DESCRIPTION FROM board",mapper);
	}
	

	@Override
	public List<Board> findAllByUserId(String userid) {
		return jdbcTemplate
				.query("SELECT ID, NAME, DESCRIPTION FROM board WHERE USERID=?", mapper, userid);
	}

	@Override
	public void create(Board board) {
		jdbcTemplate.update("INSERT INTO board VALUES(?,?,?, ?)", 
				board.getId(), board.getName(), board.getDescription(), board.getUserId());
	}

	@Override
	public void delete(Board board) {
		jdbcTemplate.update("DELETE FROM boards WHERE ID=?", board.getId());
	}

	@Override
	public void update(Board board) {
		jdbcTemplate.update("UPDATE board SET NAME=?, DESCRIPTION=? WHERE ID=?",
				board.getName(), board.getDescription());
	}

}

class boardMapper implements RowMapper<Board> {
	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board board = new Board();
		board.setId(rs.getString("ID"));
		board.setName(rs.getString("NAME"));
		board.setDescription(rs.getString("DESCRIPTION"));
		return board;
	}
}
