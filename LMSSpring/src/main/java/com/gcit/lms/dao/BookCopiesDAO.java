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
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibBranch;

public class BookCopiesDAO extends BaseDAO<BookCopies> implements ResultSetExtractor<List<BookCopies>> {
	private static final String SELECT_ALL = "select * from tbl_book_copies";
	private static final String SELECT_ONE = "select * from tbl_book_copies where bookId = ? and branchId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_book_copies(bookId, branchId,noOfCopies)values(?,?,?)";
	private static final String DELETE = "delete from tbl_book_copies where bookId = ? and branchId = ?";
	private static final String UPDATE = "update tbl_book_copies set noOfCopies = noOfCopies + ? where bookId = ? and branchId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_book_copies";
	private static final String GET_NO_COPIES ="SELECT noOfCopies FROM tbl_book_copies"
			+ " where branchId = ? and bookId = ?";

	public void create(BookCopies copy) throws SQLException {	
		template.update(INSERT, new Object[]{copy.getBook().getBookId(), 
				copy.getBranch().getBranchId(), copy.getNoOfCopies()});

	}

	public void update(BookCopies copy) throws SQLException{
		template.update(UPDATE, new Object[]{copy.getNoOfCopies(), copy.getBook().getBookId(), 
				copy.getBranch().getBranchId()});

	}

	public void delete(BookCopies copy)throws SQLException {
		template.update(DELETE, new Object[]{copy.getBook().getBookId(), copy.getBranch().getBranchId()});

	}
	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public Integer getNoCopyByBranch(int branchId, int bookId) throws SQLException{
		return template.queryForObject(GET_NO_COPIES, new Object[]{branchId, bookId}, Integer.class);
	}

	public BookCopies read(BookCopies bc) throws SQLException {
		return null;
	}

	public BookCopies read(int pk1, int pk2) throws SQLException {
		return (BookCopies) template.query(SELECT_ONE, new Object[]{pk1, pk2}, this);
	}

	public List<BookCopies> readAll(String sql, Object[] vals) throws SQLException {
		return template.query(SELECT_ALL, this);
	}

	public List<BookCopies> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<BookCopies> list = new ArrayList<BookCopies>();
		Book b = new Book();
		LibBranch br = new LibBranch();
		while(rs.next()){
			int bookId = rs.getInt("bookId");
			int branchId = rs.getInt("branchId");
			b.setBookId(bookId);
			br.setBranchId(branchId);
			BookCopies bc = new BookCopies();
			bc.setBook(b);
			bc.setBranch(br);
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			list.add(bc);
		}

		return list;
	}

}
