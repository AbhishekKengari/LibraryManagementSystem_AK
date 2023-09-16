package com.book.bookDAO;

import com.book.list.BookList;
import com.dbOperations.DBOperations;
import com.model.user.User;

public class BookDAO {

	DBOperations dbOp = new DBOperations();

	public BookList fetchAvailableBooks() throws Exception {
		return dbOp.fetchAvailableBooks();
	}

	public void borrowbok(int user_id, String book_name,BookList list) throws Exception{
		dbOp.borrowbook(user_id,book_name,list);
		
	}
	

	public void returnBorrowedBook(int user_id,int bookID) throws Exception {
		dbOp.returnBorrowedBook(user_id,bookID);
		 
		
	}

	public BookList fetchBorrowedBooks(int userid) throws Exception{
		
		User a = new User(userid);
		return dbOp.fetchBorrowedBook(a.getUserID());
	}

}
