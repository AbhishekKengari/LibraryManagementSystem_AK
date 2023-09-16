package com.model.book;

import java.sql.Date;
import java.time.LocalDate;

public class Book {

	private int b_id;
	private String book_name;
	LocalDate issue_date;
	LocalDate due_date;
	LocalDate return_date;
	
		public Book(int b_id, String book_name, LocalDate issue_date, LocalDate due_date) {
		super();
		this.b_id = b_id;
		this.book_name = book_name;
		this.issue_date = issue_date;
		this.due_date = due_date;
		
	}

	public Book(LocalDate issue_date, LocalDate due_date, LocalDate return_date) {
		super();
		this.issue_date = issue_date;
		this.due_date = due_date;
		this.return_date = return_date;
	}

	public Book() {}
	
	public Book(int b_id,String book_name) {
		this.b_id=b_id;
		this.book_name=book_name;
	}
	
	
	public int getid() {
		return b_id;
	}
	public String getName() {
		return book_name;
	}
	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}


	
	
}
