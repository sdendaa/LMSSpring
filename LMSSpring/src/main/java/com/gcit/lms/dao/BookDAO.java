/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;


public class BookDAO extends BaseDAO<Book>  implements ResultSetExtractor<List<Book>>{
	private static final String SELECT_ALL = "select * from tbl_book";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book where title like ?";
	private static final String SELECT_ONE = "select * from tbl_book where bookId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_book (title, pubId) values (?,?)";
	private static final String DELETE = "delete from tbl_book where bookId = ?";
	private static final String UPDATE = "update tbl_book set title = ? where bookId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_book";
	private static final String SELECT_ALL_BRANCH = "SELECT * FROM tbl_book b"
			+ " join tbl_book_copies bc on b.bookId = bc.bookId"
			+ " join tbl_library_branch br on bc.branchId = br.branchId"
			+ " where br.branchId = ?";
	private static final String SELECT_ALL_SEARCH_BY_BRANCH ="SELECT title FROM tbl_book b"
			+ " join tbl_book_loans bl on b.bookId = bl.bookId"
			+ " join tbl_library_branch br on bl.branchId = br.branchId"
			+ " where br.branchId = ? and title like ?";
	private static final String SELECT_BORROWED_BOOK = "SELECT * FROM tbl_book b"
			+ " join tbl_book_loans bl on b.bookId = bl.bookId"
			+ " join tbl_library_branch br on bl.branchId = br.branchId"
			+ " where br.branchId = ? and bl.cardNo = ?";
	private static final String INSERT_BA = "insert into tbl_book_authors (bookId, authorId) values (?,?)";
	private static final String INSERT_BG = "insert into tbl_book_genres (genre_id, bookId) values (?,?)";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_book where title like ?";
	private static final String DELETE_BG = "delete from tbl_book_genres where bookId = ?";
	private static final String DELETE_BA = "delete from tbl_book_authors where bookId = ?";
	
	@Autowired
	JdbcTemplate template;

	public void create(Book book) throws SQLException {
		int bookId = addBookWithId(book);
		if (book.getAuthors() != null) {
			for (Author a : book.getAuthors()) {
				template.update(INSERT_BA, new Object[]{bookId, a.getAuthorId()});
			}
		}
		if(book.getBranch()!=null){
			template.update("insert into tbl_book_copies(bookId,branchId, noOfCopies) values(?,?,?)", new Object[]{bookId, book.getBranch().getBranchId(),1});
		}
//		if (book.getGenres() != null) {
//			for (Genre g : book.getGenres()) {
//				template.update(INSERT_BG, new Object[] { g.getGenreId(), bookId });
//			}
//		}
	}

	public void update(Book book) throws SQLException{
		template.update(UPDATE, new Object[]{book.getTitle(), book.getBookId()});
//		template.update(DELETE_BG, new Object[] { book.getBookId() });
//		template.update(DELETE_BA, new Object[] { book.getBookId() });
//		if (book.getGenres() != null) {
//			for (Genre g : book.getGenres()) {
//				template.update(INSERT_BG, new Object[] { g.getGenreId(), book.getBookId() });
//			}
//		}
//		if (book.getAuthors() != null) {
//			for (Author a : book.getAuthors()) {
//				template.update(INSERT_BA, new Object[] { book.getBookId(), a.getAuthorId() });
//			}
//		}
	}

	public void delete(Book book) throws SQLException{
		template.update(DELETE, new Object[]{book.getBookId()});
	}

	public Book readBookByID(int bookId) throws SQLException {
		List<Book> books = template.query(SELECT_ONE, new Object[]{bookId}, this);
		if(books != null){
			return books.get(0);
		}
		return null;
	}
	public List<Book> readAllBooks(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
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
	public Integer getBookCountByName(String searchString) throws SQLException{
		searchString = "%"+searchString+"%";
		return template.queryForObject(GET_COUNT_BY_NAME, new Object[]{searchString}, Integer.class);
	}
	public List<Book> readBooksByName(String searchString, Integer pageNo) throws SQLException {
		searchString = "%"+searchString+"%";
		setPageNo(pageNo);
		return template.query(SELECT_ALL_SEARCH, new Object[] {searchString}, this);
	}
	public Integer getBookCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public Integer getCountByBranch(Integer branchId) throws SQLException{
		return template.queryForObject(SELECT_ALL_BRANCH, new Object[]{branchId}, Integer.class);
	}

	public List<Book> getAllBooksByBranch(Integer branchId, Integer pageNo,Integer pageSize, String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if(searchString !=null && !"".equals(searchString)){
			List<Object> valueList = new ArrayList<Object>();
			searchString = "%"+searchString+"%";
			valueList.add(searchString);
			return template.query(SELECT_ALL_SEARCH_BY_BRANCH, new Object[]{branchId, searchString}, this);
		}else{
			return template.query(SELECT_ALL_BRANCH, new Object[]{branchId}, this);
		}
	}

	public List<Book> getAllBooksBorrowedByBranch(Integer branchId, Integer cardNo) throws SQLException {
		return template.query(SELECT_BORROWED_BOOK, new Object[]{branchId, cardNo}, this);
	}

	public void createBookAuthor(Integer bookId, Integer authorId) throws SQLException {

	}

	public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Book> list = new ArrayList<Book>();
		while(rs.next()){
			Book bo = new Book();
			int publId = rs.getInt("pubId");
			Publisher pu = new Publisher();
			pu.setPublisherId(publId);

			bo.setBookId(rs.getInt("bookId"));
			bo.setTitle(rs.getString("title"));
			bo.setPublisher(pu);
			list.add(bo);
		}

		return list;
	}
	public Integer addBookWithId(Book b){
		final String title = b.getTitle();
		final Integer pubId = b.getPublisher().getPublisherId();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT,
						new String[] { "bookId" });
				ps.setString(1, title);
				ps.setInt(2, pubId);
				return ps;
			}

		}, keyHolder);
		Integer bookId = keyHolder.getKey().intValue();
		return bookId;
	}
}
