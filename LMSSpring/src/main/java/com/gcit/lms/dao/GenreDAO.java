/**
 * 
 */
package com.gcit.lms.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;


public class GenreDAO extends BaseDAO<Genre> implements ResultSetExtractor<List<Genre>>{

	private static final String SELECT_ALL = "select * from tbl_genre";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_author where authorName like ?";
	private static final String SELECT_ONE = "select * from tbl_genre where genre_id = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_genre(genre_name)values(?)";
	private static final String DELETE = "delete from tbl_genre where genre = ?";
	private static final String UPDATE = "update tbl_genre set genre_name = ? where genre_id = ?";
	private static final String GET_COUNT = "select count(*) from tbl_author";
	private static final String SELECT_ALL_FROM_BOOK =  "SELECT g.genre_id, g.genre_name FROM tbl_genre g "
			+ " join tbl_book_genres bg on bg.genre_id = g.genre_id"
			+ " join tbl_book b on b.bookId = bg.bookId"
			+ " where b.bookId = ?";

	public void create(Genre genre) throws SQLException {
		template.update(INSERT, new Object[]{genre.getGenreName()});

	}

	public void update(Genre genre) throws SQLException{
		template.update(UPDATE, new Object[]{genre.getGenreName(), genre.getGenreId()});

	}

	public void delete(Genre genre) throws SQLException{
		template.update(DELETE, new Object[]{genre.getGenreId()});

	}

	public Genre read(Genre g) throws SQLException {
		return (Genre) template.query(SELECT_ONE, new Object[]{g.getGenreId()}, this);
	}

	public List<Genre> readAll(String sql, Object[] vals) throws SQLException {
		return template.query(SELECT_ALL,  this);
	}

	public List<Genre> getAllGenre() throws SQLException {
		return template.query(SELECT_ALL, this);
	}

	public List<Genre> getAllGenresBooK() throws SQLException {
		Book myBook = new Book();
		return template.query(SELECT_ALL_FROM_BOOK, new Object[]{myBook.getBookId()}, this);
	}

	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public List<Genre> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Genre> list = new ArrayList<Genre>();
		while(rs.next()){
			Genre ger = new Genre();
			ger.setGenreId(rs.getInt("genre_id"));
			ger.setGenreName(rs.getString("genre_name"));

			list.add(ger);
		}
		return list;
	}
}
