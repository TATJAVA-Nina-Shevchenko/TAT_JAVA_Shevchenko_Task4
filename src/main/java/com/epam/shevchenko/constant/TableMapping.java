package com.epam.shevchenko.constant;

public class TableMapping {
	
	// book table
	public static final String COLUMN_NAME_BOOK_ID = "id";
	public static final String COLUMN_NAME_BOOK_TITLE = "title" ;
	public static final String COLUMN_NAME_BOOK_AUTHOR= "author" ;
	public static final String COLUMN_NAME_BOOK_STATUS = "book_status_id";

	//user table
	public static final String COLUMN_NAME_USER_ID = "id";
	public static final String COLUMN_NAME_USER_LOGIN = "login" ;
	public static final String COLUMN_NAME_USER_PASSWORD= "password" ;
	public static final String COLUMN_NAME_USER_TELEPHONE= "contact_data" ;
	public static final String COLUMN_NAME_USER_STATUS= "user_status_id" ;
	
	//history table
	public static final String COLUMN_NAME_ORDER_ID = "id";
	public static final String COLUMN_NAME_ORDER_USER = "user_id";
	public static final String COLUMN_NAME_ORDER_STATUS = "order_status_id";
	
	//history_has_books table
	public static final String COLUMN_NAME_ORDER_BOOKS_ID = "history_id";
	public static final String COLUMN_NAME_ORDER_BOOKS_BOOK= "books_id";
		
	//user status table
	public static final String COLUMN_NAME_USER_STATUS_ID = "id";
	public static final String COLUMN_NAME_USER_STATUS_STATUS= "status" ;
	
	//book status table
	public static final String COLUMN_NAME_BOOK_STATUS_ID = "id";
	public static final String COLUMN_NAME_BOOK_STATUS_STATUS= "status" ;
	
	//order status table
	public static final String COLUMN_NAME_ORDER_STATUS_ID = "id";
	public static final String COLUMN_NAME_ORDER_STATUS_STATUS= "status" ;


	

}
