/**
 * 
 */
package com.gcit.lms.entity;


public class BookCopies  {
	private int noOfCopies;
	private  LibBranch branch;
	private  Book book;
	
	
	public LibBranch getBranch() {
		return branch;
	}

	public void setBranch(LibBranch branch) {
		this.branch = branch;
	}

	public  Book getBook() {
		return book;
	}

	public  void setBook(Book book) {
		this.book = book;
	}
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
