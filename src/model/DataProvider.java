package model;

import java.util.ArrayList;
import java.util.Collection;

public class DataProvider {

	private Collection<BookTo> books;

	public DataProvider() {
		books = new ArrayList<BookTo>();
	}

	public void setBooks(Collection<BookTo> books) {
		this.books = books;
	}

	public Collection<BookTo> getBooks() {
		return books;
	}

}
