/**
 * 
 */
package com.gcit.lms.entity;

import java.util.ArrayList;
import java.util.List;


public class Borrower  {
	private int cardNo;
	private String name;
	private String address;
	private String phone;
	
	private static List<BookLoan> loan;
	
	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static List<BookLoan> getLoan() {
		return loan;
	}

	public static void setLoan(List<BookLoan> loan) {
		Borrower.loan = loan;
	}
	public void addLoan(BookLoan bl){
		if(loan == null){
			loan = new ArrayList<BookLoan>();
			loan.add(bl);
		}
	}
}
