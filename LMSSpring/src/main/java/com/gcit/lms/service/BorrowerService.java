/**
 * 
 */
package com.gcit.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;



public class BorrowerService {
	@Autowired
	BookLoanDAO blDAO;

	@Autowired
	BranchDAO brDAO;

	@Autowired
	BookCopiesDAO bcDAO;
	
	@Autowired
	BorrowerDAO borDAO;
	
	@Autowired
	BookDAO bDAO;

	public void checkOutBook(BookLoan bl, BookCopies bc)throws Exception{
		blDAO.create(bl);
		bcDAO.update(bc);
	}

	public LibBranch getLibraryBranchById(int branchId) throws SQLException{
		return brDAO.readByID(branchId);

	}

	public void returnBook(BookLoan bl, BookCopies bc)throws Exception{
		blDAO.delete(bl);
		bcDAO.update(bc);

	}

	public Borrower getBorrowerById(Integer borrowerId) throws SQLException {
		return borDAO.getBorrowerById(borrowerId);

	}

	public List<Book> getAllBorrowedBooks(Integer branchId, Integer cardNo) throws SQLException{
		return bDAO.getAllBooksBorrowedByBranch(branchId, cardNo);
	}
	public boolean cardAuthentication(int cardNo) throws  SQLException {
		Borrower b = getBorrowerById(cardNo);
		boolean valid=false;
		try {
			if(b == null){
				valid=false; 
			}else{
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  valid;
	}

}
