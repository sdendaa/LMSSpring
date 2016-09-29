/**
 * 
 */
package com.gcit.lms.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibBranch;


public class LibrarianService {

	@Autowired
	BookDAO bDAO;

	@Autowired
	BranchDAO brDAO;
	
	@Autowired
	BookCopiesDAO bcDAO;
	
	public void updateLibraryDetail(LibBranch br) throws Exception{
			brDAO.update(br);
	}

	public void AddBookCopies(BookCopies bc) throws Exception{
		bcDAO.update(bc);
	}
	public List<Book> getAllBooksFromBranch(Integer branchId, Integer pageNo,String searchString) throws SQLException{
		List<Book> bookList = new ArrayList<Book>();
		bookList = bDAO.getAllBooksByBranch(branchId, pageNo, 10, searchString);
		return bookList;
	}
	public Integer getNoOfCopyByBranch(Integer branchId, Integer bookId) throws SQLException{
		return bcDAO.getNoCopyByBranch(branchId, bookId);
	}
}