/**
 * 
 */
package com.gcit.lms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LibBranch {
	private  int branchId;

	private String branchName;
	private String branchAddress;
	
	private HashMap<Book, Integer> NoCopies;
	private List<BookLoan> bookLoans;
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public HashMap<Book, Integer> getNoCopies() {
		return NoCopies;
	}
	public void setNoCopies(HashMap<Book, Integer> noCopies) {
		NoCopies = noCopies;
	}
	public void addBookCopies(Book b){
		if(NoCopies == null){
			NoCopies = new HashMap<Book, Integer>();
			NoCopies.put(b, 1);
		}
	}
	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}
	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}
	public void addBookLoan(BookLoan bl){
		if(bookLoans == null){
			bookLoans = new ArrayList<BookLoan>();
			bookLoans.add(bl);
		}
	}
}
