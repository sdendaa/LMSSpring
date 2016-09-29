package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	AdminService service;

	@Autowired
	LibrarianService libService;

	@Autowired
	BorrowerService borService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	//The book service
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String addBook() {
		return "addBook";

	}
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBookPro(@RequestParam("title") String title, @RequestParam("pubId") int pubId,
			@RequestParam(value = "authorId", required = false) String[] authors,
			@RequestParam("branchId") int branchId) throws Exception {
		ArrayList<Author> authorList = new ArrayList<Author>();
		Book book = new Book();
		if (authors != null) {
			for (int i = 0; i < authors.length; i++) {
				Author author = new Author();
				author=service.getAuthorByID(Integer.parseInt(authors[i]));
				authorList.add(author);
			}

			LibBranch br = new LibBranch();
			br.setBranchId(branchId);
			book.setTitle(title);
			Publisher pub=new Publisher();
			pub.setPublisherId(pubId);
			book.setPublisher(pub);
			book.setBranch(br);
			book.setAuthors(authorList);
			
			service.addBook(book);
		}

		return "showAllBooks";
	}
	// show book
	@RequestMapping(value = "/showAllBooks", method = RequestMethod.GET)
	public String BooksView() throws SQLException {
		service.getAllBooks(1, null);
		return "showAllBooks";
	}
	//delete book
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(Model model ){		
		return "deleteBook";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public String deleteBookPro(@RequestParam(value="bookId")int bookId) throws SQLException{
		Book b = new Book();
		b.setBookId(bookId);
		service.deleteBook(b);
		return "showAllBooks";
	}
	//edit book
	@RequestMapping(value = "/editBook", method = RequestMethod.GET)
	public String editBook(Model model ){		
		return "editBook";
	}

	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public String editBookPro(@RequestParam(value="bookId") int bookId,
			@RequestParam(value="title") String title) throws Exception{
		Book b = new Book();
		b.setBookId(bookId);
		b.setTitle(title);
		service.updateBook(b);
		return "showAllBooks";
	}


	//The author service
	@RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
	public String addView() {
		return "addAuthor";

	}

	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
	public String addAuthor(@RequestParam("authorName") String name) throws Exception {
		Author author = new Author();
		author.setAuthorName(name);
		service.addAuthor(author);

		return "showAllAuthors";
	}
	//show author
	@RequestMapping(value = "/showAllAuthors", method = RequestMethod.GET)
	public String authorView() throws SQLException {
		service.getAllAuthors(1, null);
		return "showAllAuthors";
	}
	//delete author
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String deleteView(Model model ){		
		return "deleteAuthor";
	}

	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
	public String deleteAuthorPro(@RequestParam(value="authorId")int authorId) throws SQLException{
		Author a = new Author();
		a.setAuthorId(authorId);
		service.deleteAuthor(a);
		return "showAllAuthors";
	}
	//edit author
	@RequestMapping(value = "/editAuthor", method = RequestMethod.GET)
	public String editAuthor(Model model ){		
		return "editAuthor";
	}

	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String editAuthorPro(@RequestParam(value="authorId") int authorId,@RequestParam(value="authorName") String authorName) throws Exception{
		Author a = new Author();
		a.setAuthorId(authorId);
		a.setAuthorName(authorName);
		service.updateAuthor(a);
		return "showAllAuthors";
	}

	//The Branch service
	@RequestMapping(value = "/addBranch", method = RequestMethod.GET)
	public String addBranch() {
		return "addBranch";

	}
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST)
	public String addBranchPro(@RequestParam("branchName") String branchName, 
			@RequestParam("branchAddress") String branchAddress) throws Exception {
		LibBranch br = new LibBranch();
		br.setBranchName(branchName);
		br.setBranchAddress(branchAddress);
		service.AddBranch(br);

		return "showAllBranchs";
	}
	//show branch
	@RequestMapping(value = "/showAllBranchs", method = RequestMethod.GET)
	public String branchView() throws SQLException {
		service.getAllBranchs(1, null);
		return "showAllBranchs";
	}
	//delete branch
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
	public String deleteBranch(Model model ){		
		return "deleteBranch";
	}
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.POST)
	public String deletebranchPro(@RequestParam(value="branchId") int branchId) throws Exception{
		LibBranch br = new LibBranch();
		br.setBranchId(branchId);
		service.deleteBranch(br);
		return "showAllBranchs";
	}
	//edit branch
	@RequestMapping(value = "/editBranch", method = RequestMethod.GET)
	public String editBranch(Model model ){		
		return "editBranch";
	}

	@RequestMapping(value = "/editBranch", method = RequestMethod.POST)
	public String editBranchPro(@RequestParam(value="branchId") int branchId,@RequestParam(value="branchName") String branchName, 
			@RequestParam(value="branchAddress") String branchAddress) throws Exception{
		LibBranch br = new LibBranch();
		br.setBranchId(branchId);
		br.setBranchName(branchName);
		br.setBranchAddress(branchAddress);
		service.updateBranch(br);

		return "showAllBranchs";
	}

	//The Borrower service
	@RequestMapping(value = "/addBorrower", method = RequestMethod.GET)
	public String addBorrower() {
		return "addBorrower";

	}
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST)
	public String addBorrowerPro(@RequestParam("name") String name, 
			@RequestParam("address") String address, @RequestParam("phone") String phone) throws Exception {
		Borrower bo = new Borrower();
		bo.setName(name);
		bo.setAddress(address);
		bo.setPhone(phone);
		service.AddBorrower(bo);

		return "showAllBorrowers";
	}
	//show all borrower
	@RequestMapping(value = "/showAllBorrowers", method = RequestMethod.GET)
	public String borrowerView() throws SQLException {
		service.getAllBorrowers(1, null);
		return "showAllBorrowers";
	}
	//delete borrower
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.GET)
	public String deleteBorrower(Model model ){		
		return "deleteBorrower";
	}
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.POST)
	public String deleteBorrowerPro(@RequestParam("cardNo") int cardNo) throws Exception{
		Borrower bo = new Borrower();
		bo.setCardNo(cardNo);
		service.deleteBorrower(bo);
		return "showAllBorrowers";
	}
	//edit borrower
	@RequestMapping(value = "/editBorrower", method = RequestMethod.GET)
	public String editBorrower(Model model ){		
		return "editBorrower";
	}

	@RequestMapping(value = "/editBorrower", method = RequestMethod.POST)
	public String editBorrowerPro(@RequestParam(value="cardNo") int cardNo,@RequestParam("name") String name, 
			@RequestParam("address") String address, @RequestParam("phone") String phone) throws Exception{
		Borrower bo = new Borrower();
		bo.setCardNo(cardNo);
		bo.setName(name);
		bo.setAddress(address);
		bo.setPhone(phone);
		service.updateBorrower(bo);
		return "showAllBorrowers";
	}
	//The publisher service
	@RequestMapping(value = "/addPublisher", method = RequestMethod.GET)
	public String addPublisher() {
		return "addPublisher";

	}
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
	public String addPublisherPro(@RequestParam("publisherName") String publisherName, 
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws Exception {
		Publisher pu = new Publisher();
		pu.setPublisherName(publisherName);
		pu.setPublisherAddress(publisherAddress);
		pu.setPublisherPhone(publisherPhone);
		service.AddPublisher(pu);
		return "showAllPublishers";
	}
	//show publishers
	@RequestMapping(value = "/showAllPublishers", method = RequestMethod.GET)
	public String publisherView() throws SQLException {
		service.getAllPublishers(1, null);
		return "showAllPublishers";
	}
	//delete publisher
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String deletePublisher(Model model ){		
		return "deletePublisher";
	}

	@RequestMapping(value = "/deletePublisher", method = RequestMethod.POST)
	public String deletePublisherPro(@RequestParam("publisherId") int publisherId) throws Exception{
		Publisher pu = new Publisher();
		pu.setPublisherId(publisherId);
		service.deletePublisher(pu);
		return "showAllPublishers";
	}
	//edit publisher
	@RequestMapping(value = "/editPublisher", method = RequestMethod.GET)
	public String editPublisher(Model model ){		
		return "editPublisher";
	}
	@RequestMapping(value = "/editPublisher", method = RequestMethod.POST)
	public String editPublisherPro(@RequestParam("publisherId") int publisherId,@RequestParam("publisherName") String publisherName, 
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws Exception{
		Publisher pu = new Publisher();
		pu.setPublisherId(publisherId);
		pu.setPublisherName(publisherName);
		pu.setPublisherAddress(publisherAddress);
		pu.setPublisherPhone(publisherPhone);
		service.updatePublisher(pu);

		return "showAllPublishers";
	}
	//BookLoan service
	@RequestMapping(value = "/showAllBookLoaned", method = RequestMethod.GET)
	public String bookLoanView() throws SQLException{
		service.getAllBooksLoaned(1, null);
		return "showAllBookLoaned";
	}
	// edit book loan
	@RequestMapping(value = "/editBookloan", method = RequestMethod.GET)
	public String overRideDueDate(Model model ){		
		return "editBookloan";
	}

	@RequestMapping(value = "/editBookloan", method = RequestMethod.POST)
	public String overRideDueDatePro(@RequestParam("branchId") Integer branchId, @RequestParam("bookId") Integer bookId,
			@RequestParam("cardNo") Integer cardNo, @RequestParam("dateOut") Date dateOut,
			@RequestParam("dueDate") Date dueDate) throws Exception{

		service.overRideDueDate(bookId, branchId, cardNo, dateOut, dueDate);

		return "showAllBookLoaned";
	}

	//view all Librarian branchs
	@RequestMapping(value = "/showLibrarianBranchs", method = RequestMethod.GET)
	public String editBranchLib(Model model ) throws SQLException{
		service.getAllBranchs(1, null);
		return "showLibrarianBranchs";
	}
	//Edit librarian branch
	@RequestMapping(value = "/editBranchLibr", method = RequestMethod.GET)
	public String viewEditBranchLib(Model model ){		
		return "editBranchLibr";
	}

	@RequestMapping(value = "/editBranchLibr", method = RequestMethod.POST)
	public String editBranchLibPro(@RequestParam("branchId") int branchId,
			@RequestParam("branchName") String branchName, 
			@RequestParam("branchAddress") String branchAddress) throws Exception{
		LibBranch br = new LibBranch();
		br.setBranchName(branchName);
		br.setBranchId(branchId);
		br.setBranchAddress(branchAddress);
		service.updateBranch(br);

		return "showLibrarianBranchs";
	}
	//view all book from each branch
	@RequestMapping(value = "/showAllBookByBranch", method = RequestMethod.GET)
	public String selectBranchLib(@RequestParam("branchId") int branchId,Model model ) throws SQLException{
		libService.getAllBooksFromBranch(branchId, null, null);
		return "showAllBookByBranch";
	}
	//add number of copies book to branch
	@RequestMapping(value = "/addNoCopies", method = RequestMethod.GET)
	public String viewAddNoCopies(Model model ){		
		return "addNoCopies";
	}

	@RequestMapping(value = "/addNoCopies", method = RequestMethod.POST)
	public String viewAddNoCopiesPro(@RequestParam("branchId") int branchId,
			@RequestParam("bookId") int bookId, 
			@RequestParam("noOfCopies") int noOfCopies) throws Exception{
		LibBranch br = new LibBranch();
		br.setBranchId(branchId);
		Book b = new Book();
		b.setBookId(bookId);
		BookCopies bc = new BookCopies();
		bc.setBook(b);
		bc.setBranch(br);
		bc.setNoOfCopies(noOfCopies);
		libService.AddBookCopies(bc);
		return "showAllBookByBranch";
	}
	//open login form
	@RequestMapping(value = "/cardAuthenCheckOut", method = RequestMethod.GET)
	public String viewCardAuthenB(Model model ){		
		return "cardAuthenCheckOut";
	}
	//submit the login 
	@RequestMapping(value = "/cardAuthenB", method = RequestMethod.POST)
	public String submitCardAuthenBPro(@RequestParam("cardNo") Integer cardNo, Model model) throws Exception{
		return "showAllBranchsBor";
//		boolean valid = borService.cardAuthentication(cardNo);
//		if(valid == true){
//			return "showAllBranchsBor";
//		}else{
//			return "cardAuthenCheckOut";	
//		}
	}
	//view all book from each branch to borrower
	@RequestMapping(value = "/showAllBookToBorrowByBranch", method = RequestMethod.GET)
	public String selectBranchBor(@RequestParam("branchId") int branchId, Model model ) throws SQLException{
		libService.getAllBooksFromBranch(branchId, null, null);
		return "showAllBookToBorrowByBranch";
	}
	//view all book from each branch to borrower
	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String checkOutBook(@RequestParam("branchId") int branchId,@RequestParam("cardNo") int cardNo,
			@RequestParam("bookId") int bookId) throws Exception{
		if(libService.getNoOfCopyByBranch(branchId, bookId)>0){
			System.out.println(bookId);
			Book b = new Book();
			b.setBookId(bookId);

			LibBranch br = new LibBranch();
			br.setBranchId(branchId);

			Borrower bo = new Borrower();
			bo.setCardNo(cardNo);

			BookCopies bc = new BookCopies();
			bc.setNoOfCopies(-1);
			bc.setBook(b);
			bc.setBranch(br);

			BookLoan bl = new BookLoan();
			bl.setBranch(br);
			bl.setBook(b);
			bl.setBorrower(bo);

			borService.checkOutBook(bl, bc);

			return "showAllBookToBorrowByBranch";
			
		}else{
			return null;
		}
		
		
	}
	//open login form
	@RequestMapping(value = "/cardAuthenReturn", method = RequestMethod.GET)
	public String viewCardAuthenR(Model model ){		
		return "cardAuthenReturn";
	}
	//submit the login 
	@RequestMapping(value = "/cardAuthenR", method = RequestMethod.POST)
	public String submitCardAuthenRPro(@RequestParam("cardNo") Integer cardNo) throws Exception{
		Borrower bo = new Borrower();
		bo.setCardNo(cardNo);
		return "showAllBranchToReturn";
	}
	//view all book from each branch to return
	@RequestMapping(value = "/showAllBookBorrowedByBranch", method = RequestMethod.GET)
	public String selectBranchToReturn(@RequestParam("cardNo") int cardNo, 
			@RequestParam("branchId") int branchId,Model model ) throws SQLException{
		borService.getAllBorrowedBooks(branchId, cardNo);
		return "showAllBookBorrowedByBranch";
	}

	//view all book from each branch to borrower
	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)
	public String returnBook(@RequestParam("branchId") int branchId,@RequestParam("cardNo") int cardNo,
			@RequestParam("bookId") int bookId) throws Exception{
		Book b = new Book();
		b.setBookId(bookId);
		
		LibBranch br = new LibBranch();
		br.setBranchId(branchId);
		
		Borrower bo = new Borrower();
		bo.setCardNo(cardNo);

		BookCopies bc = new BookCopies();
		bc.setNoOfCopies(1);
		bc.setBook(b);
		bc.setBranch(br);

		BookLoan bl = new BookLoan();
		bl.setBranch(br);
		bl.setBook(b);
		bl.setBorrower(bo);
		
		borService.returnBook(bl, bc);
		
		return "showAllBookToBorrowByBranch";
	}

}
