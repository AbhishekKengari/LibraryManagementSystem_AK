package com.libraryController;
import com.user.userDAO.*;
import com.book.bookDAO.*;
import com.transaction.transactionDAO.*;
import com.user.userView.*;
import com.book.bookView.*;
import com.book.list.BookList;
public class LibraryController {

	private UserDAO userDAO;
	private BookDAO bookDAO;
	private TransactionDAO transactionDAO;
    private UserView userView;
    private BookView bookView;
    private BookList list;
    
    public LibraryController() {}


	public LibraryController(UserDAO userDAO, BookDAO bookDAO, TransactionDAO transactionDAO, UserView userView,
			BookView bookView) {

		this.userDAO = userDAO;
		this.bookDAO = bookDAO;
		this.transactionDAO = transactionDAO;
		this.userView = userView;
		this.bookView = bookView;
	}
	

	public boolean checkUserExist(int user_id) throws Exception{
	return	userDAO.checkUserExist(user_id);
		 
	}


	public void registerNewUser(String user_name) throws Exception {
		userDAO.registerNewUser(user_name);
		System.out.println("Remember your Unique 'User ID' for reference");
	}


	public void  fetchAvailableBooks() throws Exception {
		list= bookDAO.fetchAvailableBooks();
		bookView.printAvailableBooks(list);
	}


	public void borrowbook(int user_id, String book_name) throws Exception {
		bookDAO.borrowbok( user_id, book_name,list);
		
	}


	public void returnBorrowedBook (int user_id,String returnbk_name) throws Exception {
		
		BookList b2= bookDAO.fetchBorrowedBooks(user_id);
		
		if(b2.getBookId(returnbk_name)==-1) {
			System.out.println("You did not borrow the book");
		}
		else 
		{
			bookDAO.returnBorrowedBook(user_id, b2.getBookId(returnbk_name));
		}
	}


	public void fetchBorrowedBooks(int userid) throws Exception {
		
		BookList b2= bookDAO.fetchBorrowedBooks(userid);
		bookView.printBorrowedBooks(b2);
	}
   
}
