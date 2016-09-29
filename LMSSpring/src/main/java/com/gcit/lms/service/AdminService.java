package com.gcit.lms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	@Autowired
	AuthorDAO aDAO;

	@Autowired
	BookDAO bDAO;

	@Autowired
	BranchDAO brDAO;

	@Autowired
	BorrowerDAO boDAO;

	@Autowired
	PublisherDAO puDAO;

	@Autowired
	BookLoanDAO blDAO;

	@Transactional
	public void addBook(Book book) throws Exception{
		if(book != null){
			if( book.getTitle() == null|| book.getTitle().length() <= 0
					|| book.getTitle().length() > 45){
				throw new Exception("Book title cannot be null or more than 45 characters");
			}
			bDAO.create(book);

		}else{
			throw new Exception("Book cannot be null or blank");
		}
	}
	@Transactional
	public void deleteBook(Book book) throws SQLException{
		bDAO.delete(book);
	}
	@Transactional
	public void updateBook(Book b)throws Exception{
		bDAO.update(b);;
	}
	public List<Book> getAllBooks(int pageNo, String searchString) throws SQLException{
		return bDAO.readAllBooks(pageNo, 10, searchString);
	}
	public Book getBookByID(Integer bookId) throws SQLException{
		return bDAO.readBookByID(bookId);
	}
	public Integer getBookCount(String searchString) throws SQLException{
		if(searchString!=null && !("").equals(searchString)){
			return bDAO.getBookCountByName(searchString);
		}else{
			return bDAO.getBookCount();
		}
	}
	public List<Book> getBooksByName(Integer pageNo, String searchString) throws SQLException{
		if(searchString!=null && !("").equals(searchString)){
			return bDAO.readBooksByName(searchString, pageNo);
		}else{
			return bDAO.readAllBooks(pageNo,10, searchString);
		}
	}
	public List<Book> getAllBooksByBranch(Integer branchId, Integer pageNo, String searchString) throws SQLException{
		return bDAO.getAllBooksByBranch(branchId, pageNo, 10, searchString);

	}
	public List<Book> getAllBooksByBorrowerByBranch(Integer branchId, Integer cardNo) throws SQLException{
		return bDAO.getAllBooksBorrowedByBranch(branchId, cardNo);
	}

	//The author service
	@Transactional
	public void addAuthor(Author author) throws Exception{
		if(author != null){
			if( author.getAuthorName() == null|| author.getAuthorName().length() <= 0
					|| author.getAuthorName().length() > 45){
				throw new Exception("author name cannot be null or more than 45 characters");
			}
			aDAO.addAuthorWithID(author);

		}else{
			throw new Exception("author cannot be null or blank");
		}

	}

	@Transactional
	public void deleteAuthor(Author author) throws SQLException{
		aDAO.delete(author);
	}

	public List<Author> getAllAuthors(int pageNo, String searchString) throws SQLException{
		List<Author> AuthorList = new ArrayList<Author>();
		AuthorList = aDAO.readAllAuthors(pageNo, 10, searchString);
		return AuthorList;
	}
	public List<Author> getAllAuthorsByBook(Integer bookId) throws SQLException{
		List<Author> AuthorList = new ArrayList<Author>();
		AuthorList = aDAO.readAllAuthorsByBook(bookId);
		return AuthorList;
	}
	public Author getAuthorByID(Integer authorID) throws SQLException{
		return aDAO.readByID(authorID);
	}

	@Transactional
	public void updateAuthor(Author a)throws Exception{
		aDAO.update(a);;
	}
	public Integer getAuthorCount(String searchString) throws SQLException{
		if(searchString!=null && !("").equals(searchString)){
			return aDAO.getAuthorCountByName(searchString);
		}else{
			return aDAO.getAuthorCount();
		}
	}

	public List<Author> readAuthorsByName(Integer pageNo, String searchString) throws SQLException{
		if(searchString!=null && !("").equals(searchString)){
			return aDAO.readAuthorsByName(searchString, pageNo);
		}else{
			return aDAO.readAllAuthors(pageNo, 10, searchString);
		}
	}


	//Branch service start here
	@Transactional
	public void AddBranch( LibBranch br)throws Exception{
		if(br != null){
			if( br.getBranchName() == null|| br.getBranchName().length() <= 0
					|| br.getBranchName().length() > 45){
				throw new Exception("branch name cannot be null or more than 45 characters");
			}
			brDAO.create(br);

		}else{
			throw new Exception("branch cannot be null or blank");
		}

	}
	@Transactional
	public void updateBranch(LibBranch br)throws Exception{
		brDAO.update(br);
	}
	@Transactional
	public void deleteBranch(LibBranch br)throws Exception{
		brDAO.delete(br);
	}
	public int getBranchsCount() throws SQLException{
		return brDAO.getCount();
	}

	public List<LibBranch> getAllBranchsToReturn(int cardNo) throws SQLException{
		List<LibBranch> branchList = new ArrayList<LibBranch>();
		branchList = brDAO.readAllBranchToReturnDAO(cardNo);
		return branchList;
	}
	public LibBranch getBranchById(int branchId) throws SQLException{
		return brDAO.readByID(branchId);
	}

	public List<LibBranch> getAllBranchs(Integer pageNo, String searchString) throws SQLException{
		List<LibBranch> branchList = new ArrayList<LibBranch>();
		branchList = brDAO.readAllBranchDAO(pageNo, 10, searchString);
		return branchList;
	}
	//Borrower service start here
	@Transactional
	public void AddBorrower(Borrower bo)throws Exception{
		if(bo != null){
			if( bo.getName() == null|| bo.getName().length() <= 0
					|| bo.getName().length() > 45){
				throw new Exception("Borrower name cannot be null or more than 45 characters");
			}
			boDAO.create(bo);

		}else{
			throw new Exception("Borrower cannot be null or blank");
		}
	}
	@Transactional
	public void updateBorrower(Borrower bo)throws Exception{
		boDAO.update(bo);

	}
	@Transactional
	public void deleteBorrower(Borrower bo)throws Exception{
		boDAO.delete(bo);

	}
	public List<Borrower> getAllBorrowers(int pageNo, String searchString) throws SQLException {
		List<Borrower> borrowerList = new ArrayList<Borrower>();
		borrowerList = boDAO.readAllBorrowerDAO(pageNo, 10, searchString);

		return borrowerList;
	}
	public int getBorrowerCount() throws SQLException{
		return boDAO.getCount();
	}
	public Borrower getBorrowerById(int cardNo) throws SQLException{
		return boDAO.getBorrowerById(cardNo);
	}
	//Publisher service start
	@Transactional
	public void AddPublisher(Publisher pu)throws Exception{
		if(pu != null){
			if( pu.getPublisherName() == null|| pu.getPublisherName().length() <= 0
					|| pu.getPublisherName().length() > 45){
				throw new Exception("Borrower name cannot be null or more than 45 characters");
			}
			puDAO.create(pu);

		}else{
			throw new Exception("Borrower cannot be null or blank");
		}
	}
	@Transactional
	public void updatePublisher(Publisher pu)throws Exception{
		puDAO.update(pu);

	}
	@Transactional
	public void deletePublisher(Publisher pu)throws Exception{
		puDAO.delete(pu);

	}
	public int getPublishersCount() throws SQLException{
		return puDAO.getCount();
	}
	public List<Publisher> getAllPublishers(int pageNo, String searchString) throws SQLException {
		List<Publisher> publisherList = new ArrayList<Publisher>();
		publisherList = puDAO.readAllPublisher(pageNo, 10, searchString);

		return publisherList;
	}
	public Publisher getPublisherById(int publisherId) throws SQLException{
		return puDAO.read(publisherId);
	}

	public void overRideDueDate(int bookId, int branchId, int cardNo, Date dateOut,Date dueDate) throws SQLException {
		LibBranch br = new LibBranch();
		br.setBranchId(branchId);
		
		Book b = new Book();
		b.setBookId(bookId);
		
		Borrower bo = new Borrower();
		bo.setCardNo(cardNo);
		
		BookLoan bl = new BookLoan();
		bl.setBranch(br);
		bl.setBook(b);
		bl.setBorrower(bo);
		bl.setCheckOutDate(dateOut);
		bl.setDueDate(dueDate);

		blDAO.update(bl);
	}

	public BookLoan getBookLoanById(int branchId, int bookId) throws SQLException{
		return blDAO.readOneLoanByBranch(branchId, bookId);
	}
	public List<BookLoan> getAllBooksLoaned(int pageNo, String searchString) {
		List<BookLoan> loanedList = new ArrayList<BookLoan>();
		loanedList = blDAO.readAllBookloaned(pageNo, 10, searchString);

		return loanedList;

	}
}
