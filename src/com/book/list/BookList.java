package com.book.list;

import com.book.*;
import com.model.book.Book;


public class BookList {
	public Node head=null;
	public Node tail= null;
	
	class Node {
		Book details;
		Node next;
	
	Node (Book details){
		this.details=details;
		next=null;
	}
	}
	public void addNode(Book details) {
	Node newNode=new Node(details);	
	if (head==null) {
		head=newNode;
		tail=newNode;
	}
	else
		{
		tail.next=newNode;
		
	tail=newNode;
		}
		}
	public void displayBookDetails() {
		System.out.println("displayboodkdetails was called");
		Node temp = null;
		temp = head;
		if (head==null) {
			System.out.println("The list is Empty");
			
		}
		else {
			while(temp!=null) {
				System.out.println(temp.details.getName());
				temp=temp.next;
			}
			
		}
	}
	public int  getBookId(String book_name) {
		Node temp=head;
		int id=-1;
		while(temp!=null) {
			if(temp.details.getName().contains(book_name)) {
				id  = temp.details.getid();
			}
			temp=temp.next;
		}
		return id;
	}
	
	public void dis_borr_book_details() {
		System.out.println("dis_borr_book_details was called");
		Node temp = null;
		temp = head;
		if (head==null) {
			System.out.println("The list is Empty");
			
		}
		else {
			while(temp!=null) {
				System.out.println(temp.details.getName()+"\t"+"  ||  "
			+temp.details.getIssue_date()+"\t"+"  ||  "+temp.details.getDue_date());
				temp=temp.next;
			}
			
		}
	}

}
