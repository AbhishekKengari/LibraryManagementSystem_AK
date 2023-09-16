package com.book.bookView;

import com.book.list.BookList;

public class BookView {
	
	public void printAvailableBooks(BookList booklist) {
		booklist.displayBookDetails();
	}

	public void printBorrowedBooks(BookList booklist) {
		
		booklist.dis_borr_book_details();
	}
}
