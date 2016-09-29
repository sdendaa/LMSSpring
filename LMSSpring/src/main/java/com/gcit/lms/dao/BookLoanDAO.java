/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;

public class BookLoanDAO extends BaseDAO<BookLoan> implements ResultSetExtractor<List<BookLoan>>{
	private static final String SELECT_ALL = "select * from tbl_book_loans";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book_loans where borrowerName like ?";
	private static final String SELECT_ONE = "select * from tbl_book_loans where branchId = ? and bookId = ?";
	private static final String SELECT_ID_BY_NAME = "select authorId from tbl_author where authorName = ?";
	private static final String INSERT = "insert into tbl_book_loans(bookId, branchId,cardNo,dateOut, dueDate, dateIn)values(?,?,?,CURDATE(), DATE_ADD(curdate(), INTERVAL 7 DAY), null)";
	private static final String DELETE = "delete from tbl_book_loans where bookId = ? and branchId = ?";
	private static final String UPDATE = "update tbl_book_loans set dateOut = ?, dueDate = ? where bookId = ? and branchId = ? and cardNo = ?";
	private static final String GET_COUNT = "select count(*) from tbl_book_loans";
	private static final String SELECT_All_From_Borrower ="SELECT title FROM tbl_book b"
			+ " join tbl_book_loans bl on b.bookId = bl.bookId"
			+ " join tbl_borrower bo on bo.cardNo = bl.cardNo"
			+ " where bl.branchId = ? and bo.cardNo = ? ";
	private static final String UPDATE_RETURN = "update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = CURDATE() where bookId = ? and branchId = ?";

	public void create(BookLoan loan) throws SQLException {
		template.update(INSERT, new Object[]{loan.getBook().getBookId(),loan.getBranch().getBranchId(), 
				loan.getBorrower().getCardNo()});

	}

	public void update(BookLoan loan) throws SQLException {
		template.update(UPDATE, new Object[]{loan.getCheckOutDate(), loan.getDueDate(), loan.getBook().getBookId(), loan.getBranch().getBranchId(),loan.getBorrower().getCardNo()});

	}
	public void updateReturn(BookLoan loan) throws SQLException {
		template.update(UPDATE_RETURN, new Object[]{loan.getCheckOutDate(),loan.getDueDate(), loan.getBook().getBookId(), loan.getBranch().getBranchId()});

	}

	public void delete(BookLoan loan) throws SQLException {
		template.update(DELETE, new Object[]{loan.getBook().getBookId(), loan.getBranch().getBranchId()});

	}

	public BookLoan readOneLoanByBranch(Integer branchId, Integer bookId) throws SQLException{
		List<BookLoan> loans = template.query(SELECT_ONE, new Object[]{branchId, bookId}, this);
		if(loans != null){
			return loans.get(0);
		}
		return null;
	}

	public Integer getCount() throws SQLException{
		return template.queryForObject(GET_COUNT, new Object[]{}, Integer.class);
	}

	public List<BookLoan> getAllBooksFromBorrowerByBranch(Integer branchId,Integer cardNo) throws SQLException{
		return template.query(SELECT_All_From_Borrower, new Object[]{branchId, cardNo}, this);	
	}

	public List<BookLoan> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<BookLoan> list = new ArrayList<BookLoan>();
		while(rs.next()){
			int branchId = rs.getInt("branchId");
			int bookId = rs.getInt("bookId");
			int cardId = rs.getInt("cardNo");
			Date dateOut = rs.getDate("dateOut");
			Date dateIn = rs.getDate("dateIn");
			Date dueDate = rs.getDate("dueDate");
			Book b = new Book();
			b.setBookId(bookId);
			LibBranch br = new LibBranch();
			br.setBranchId(branchId);
			Borrower bor = new Borrower();
			bor.setCardNo(cardId);

			BookLoan bl = new BookLoan();
			bl.setCheckOutDate(dateOut);
			bl.setCheckInDate(dateIn);
			bl.setDueDate(dueDate);
			bl.setBook(b);
			bl.setBranch(br);
			bl.setBorrower(bor);

			list.add(bl);
		}

		return list;
	}

	public List<BookLoan> readAllBookloaned(int pageNo, int pageSize, String searchString) {
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
}
