package com.libraryManagement;

import java.util.Scanner;

import com.book.bookDAO.BookDAO;
import com.book.bookView.BookView;
import com.libraryController.LibraryController;
import com.transaction.transactionDAO.TransactionDAO;
import com.user.userDAO.UserDAO;
import com.user.userView.UserView;

public class LibraryManagement {
	
public static void main(String[] args) throws Exception{
	System.out.println("Welcome!!");
	boolean logoutrequested=false;
	while(!logoutrequested) {
	System.out.println("1.Registered User ");
	System.out.println("2.Sign up ");
	System.out.println("3.Log out ");
	Scanner sc=new Scanner(System.in);
	LibraryController lc=new LibraryController(new UserDAO(), new BookDAO(),new TransactionDAO(),
			new UserView(), new BookView());
	int choice=sc.nextInt();
	switch(choice) {
	
	    case 1:
	    	System.out.println("Enter your registered User ID");
	    	int user_id=sc.nextInt();
	    	boolean exitRequested=false;
	    	if(lc.checkUserExist(user_id)) {
	    while(!exitRequested) {
	    	
	    		System.out.println("1.Borrow Book ");
	    		System.out.println("2.Show Borrowed Books ");
	    		System.out.println("3.Return Book");
	    		System.out.println("4.Exit");
	    		int option=sc.nextInt();
       	 
        	 switch(option) {
          
              case 1:
            	  
            	  lc.fetchAvailableBooks();
            	  System.out.println("Choose a book you want to borrow");
            	  String book_name=sc.next();
            	  lc.borrowbook(user_id,book_name);
            	  //get id for given b_name from book list 
            	  //enter user_id book_id issue_date and due_date into transactions
        	  break;
        	  
              case 2:
            	     System.out.println("user id used here is "+ user_id);	  
            	  lc.fetchBorrowedBooks(user_id);
        	  
        	  break;
              case 3:
            	  System.out.println("Enter a book you want to return");
            	  String returnbk_name =sc.next();
            	  lc.returnBorrowedBook(user_id,returnbk_name);
            	  
            //	  lc.returnBorrowedBook(user_id,returnbk_name);
        	  
        	  break;
              case 4:
            	  exitRequested=true;
            	break;
                		}
	    }
	    
         }
		     else 
		     {
		    	 System.out.println("User does not exists");
		     }
		break;
		
	    case 2:
	    	System.out.println("Enter Full Name to Register");
	    	String user_name=sc.next();
	    	lc.registerNewUser(user_name);
		break;
	    case 3:
	    	System.out.println("Logging out......");
	    	logoutrequested=true;
		break;
	
	}
    }
}
}