package com.dbOperations;

import com.book.list.BookList;
import com.dbConnection.DBConnection;
import com.model.book.Book;
import com.model.user.User;

import java.sql.*;
import java.time.LocalDate;
public class DBOperations {
     DBConnection con;
     
     public Statement stmtMethod(String Query ) throws Exception {
 		return 	DBConnection.getConnection().createStatement();
 		}
     
     public PreparedStatement pstmtMethod(String Query ) throws Exception {
 		return 	DBConnection.getConnection().prepareStatement(Query);
     }
 		
	public boolean checkUserExist(int user_id) throws Exception {
		String Query="SELECT * FROM Users WHERE user_id=?";
		PreparedStatement pstmt=pstmtMethod(Query);
		pstmt.setInt(1, user_id);
	
		ResultSet rs = pstmt.executeQuery(); 
		
		return rs.next();
	}
	
	public void  getUserDetails(String user_name) throws Exception {
		String Query="SELECT * FROM Users WHERE  user_name=?";
		PreparedStatement pstmt=pstmtMethod(Query);
		pstmt.setString(1, user_name);
	
		ResultSet rs = pstmt.executeQuery(); 
		
		while( rs.next()) {
			System.out.println(rs.getString("user_name")+" "+rs.getString("user_id"));
		}
		
	}
	public void registerNewUser(String user_name)  throws Exception{
		String InsertQuery="INSERT INTO users (user_name) VALUES (?)";
		PreparedStatement pstmt=pstmtMethod(InsertQuery);
		pstmt.setString(1, user_name);
		pstmt.executeUpdate();
		//pstmt.execute();
		getUserDetails(user_name);
		
	}

	public BookList fetchAvailableBooks() throws Exception {
		BookList list= new BookList();
		String fetchQuery="SELECT B.book_id, B.book_name\r\n"
				+ "FROM Book B\r\n"
				+ "LEFT JOIN Transactions T ON B.book_id = T.book_id\r\n"
				+ "WHERE T.book_id IS NULL AND T.return_date IS  NULL ";
		PreparedStatement pstmt=pstmtMethod(fetchQuery);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Book b= new Book(rs.getInt("book_id"),rs.getString("book_name"));
			list.addNode(b);
		}
		pstmt.close();
		return list;
	}

	public void borrowbook(int user_id, String name,BookList list ) throws Exception{
		
			LocalDate curr_date=LocalDate.now();
			LocalDate d_date=curr_date.plusMonths(1);
			java.sql.Date issue_date=java.sql.Date.valueOf(curr_date);
			java.sql.Date due_date=java.sql.Date.valueOf(d_date);
			int b_id=list.getBookId(name);
			String insertQuery="insert into transactions (book_id,user_id,issue_date,due_date) values  (?,?,?,?)";
			PreparedStatement pstmt=pstmtMethod(insertQuery);
			pstmt.setInt(1,b_id);
			pstmt.setInt(2, user_id);
			pstmt.setDate(3, issue_date);
			pstmt.setDate(4, due_date);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Thankyou!!!!  You just borrowed a book");
		}

	
	//public void fetchBooksByUserID(int user_id) 
	
	
	
	public void returnBorrowedBook(int user_id,int bookID) throws Exception
	{
		LocalDate curr_date=LocalDate.now();
		java.sql.Date return_date=java.sql.Date.valueOf(curr_date);
		String fetchBorrowedBooksQuery="UPDATE TRANSACTIONS SET return_date=? where User_id=? and book_id= ?";
		PreparedStatement pstmt=pstmtMethod(fetchBorrowedBooksQuery);
		pstmt.setDate(1, return_date);
		pstmt.setInt(2, user_id);
		pstmt.setInt(3, bookID);
		pstmt.executeUpdate();
		pstmt.close();
		
	}

	public BookList fetchBorrowedBook(int userid) throws Exception {
		BookList Blist=new BookList();
		String fetchBorrowedBooksQuery="SELECT B.book_id, B.book_name ,T.issue_date,T.due_date\r\n"
				+ "FROM Book B\r\n"
				+ "INNER JOIN Transactions T ON B.book_id = T.book_id\r\n"
				+ "WHERE T.user_id =? AND T.return_date IS NULL ";
		PreparedStatement pstmt=pstmtMethod(fetchBorrowedBooksQuery);
		pstmt.setInt(1, userid);
		ResultSet rs=pstmt.executeQuery();
		System.out.println("the length of list "+Blist.toString());
		while(rs.next()) {
			Book b= new Book(rs.getInt("book_id"),rs.getString("book_name"),rs.getDate("issue_date").toLocalDate(),
					rs.getDate("due_date").toLocalDate());
			Blist.addNode(b);
		}
		pstmt.close();
		return Blist;
		
	}
	

}
