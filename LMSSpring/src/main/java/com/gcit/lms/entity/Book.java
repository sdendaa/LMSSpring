package com.gcit.lms.entity;

import java.util.ArrayList;
import java.util.List;

public class Book implements BaseEntity {

	private Integer bookId;
	private String title;
	private Publisher publisher;
	private LibBranch branch;
	
	private List<Author> authors;
	private List<Genre> genres;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LibBranch getBranch() {
		return branch;
	}
	public void setBranch(LibBranch branch) {
		this.branch = branch;
	}
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void addAuthor(Author a){
		if(authors == null){
			authors = new ArrayList<Author>();
			authors.add(a);
		}
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public void addGenres(Genre g){
		if(genres == null){
			genres = new ArrayList<Genre>();
			genres.add(g);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", authorName=" + title + "]";
	}
}
