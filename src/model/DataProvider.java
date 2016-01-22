package model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.databinding.observable.list.WritableList;

public class DataProvider {

	private static DataProvider instance = null;

	protected DataProvider() {
	}

	public static DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}

	private WritableList books = new WritableList(new ArrayList<>(), BookTo.class);

	public void setBooks(Collection<BookTo> books) {
		this.books.clear();
		this.books.addAll(books);
	}

	public Collection<BookTo> getBooks() {
		return books;
	}

}
