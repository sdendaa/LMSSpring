/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;



public class AuthorDAO extends BaseDAO<Author> implements ResultSetExtractor<List<Author>>{

	private static final String SELECT_ALL = "select * from tbl_author";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_author where authorName like ?";
	private static final String SELECT_ONE = "select * from tbl_author where authorId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_author (authorName) values (?)";
	private static final String DELETE = "delete from tbl_author where authorId = ?";
	private static final String UPDATE = "update tbl_author set authorName = ? where authorId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_author";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_author where authorName like ?";
	private static final String INSERT_BOOK_AUTHOR = "insert into tbl_book_authors (bookId, authorId) values (?, ?)";
	private static final String SELECT_ALL_AUTHORS_BOOK = "SELECT * FROM tbl_author a "
			+ " join tbl_book_authors ba on ba.authorId = a.authorId"
			+ " join tbl_book b on b.bookId = ba.bookId"
			+ " where b.bookId = ?";

	public void create(Author author) throws SQLException {
		template.update(INSERT, new Object[]{author.getAuthorName()});
	}

	public Integer addAuthorWithID(Author author) {
		final String authorName = author.getAuthorName();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "authorId" });
				ps.setString(1, authorName);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();

	}
	public void addBookAuthor(Author author) throws SQLException {
		for(Book b: author.getBooks()){
			template.update(INSERT_BOOK_AUTHOR, new Object[] { author.getAuthorId(), b.getBookId() });
		}
	}

	public void update(Author author) throws SQLException {
		template.update(UPDATE, new Object[]{author.getAuthorName(), author.getAuthorId()});

	}

	public void delete(Author author) throws SQLException {
		template.update(DELETE, new Object[]{author.getAuthorId()});

	}
	public Integer getAuthorCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public Author read(Author a) throws SQLException {
		List<Author> authors = template.query(SELECT_ONE, new Object[]{a.getAuthorId()}, this);
		if(authors != null){
			return authors.get(0);
		}
		return null;
	}

	public Author readByID(Integer authorID) throws SQLException {
		List<Author> authors = template.query(SELECT_ONE, new Object[] {authorID}, this);
		if(authors!=null){
			return authors.get(0);
		}
		return null;
	}

	public List<Author> readAllAuthors(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);

		if(searchString !=null && !"".equals(searchString)){
			List<Object> valueList = new ArrayList<Object>();
			searchString = "%"+searchString+"%";
			valueList.add(searchString);
			return template.query(SELECT_ALL_SEARCH, new Object[]{searchString}, this);
		}else{
			return template.query(SELECT_ALL, this);
		}
	}

	public List<Author> readAllAuthorsByBook(int bookId) throws SQLException {
		return template.query(SELECT_ALL_AUTHORS_BOOK, new Object[]{bookId}, this);
	}

	public Integer getAuthorCountByName(String searchString) throws SQLException{
		searchString = "%"+searchString+"%";
		return template.queryForObject(GET_COUNT_BY_NAME, new Object[]{searchString}, Integer.class);
	}

	public List<Author> readAuthorsByName(String searchString, Integer pageNo) throws SQLException {
		searchString = "%"+searchString+"%";
		setPageNo(pageNo);
		return template.query(SELECT_ALL_SEARCH, new Object[] {searchString}, this);
	}

	public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Author> list = new ArrayList<Author>();
		while(rs.next()){
			Author au = new Author();
			au.setAuthorId(rs.getInt("AuthorId"));
			au.setAuthorName(rs.getString("AuthorName"));
			list.add(au);
		}

		return list;
	}
	
	public Integer getCountByName(String searchString) {
		return template.queryForObject(SELECT_ALL_SEARCH, new Object[]{searchString},Integer.class) ;
	}
}
