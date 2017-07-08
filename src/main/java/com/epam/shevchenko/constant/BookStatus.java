package com.epam.shevchenko.constant;

public enum BookStatus {

	AVAILABLE(1), RESERVED(2), lEASED(3), DELETED(4),

	//
	;
	
	 private int bookStatusCode;

	 BookStatus(int code) {
	   this.bookStatusCode = code;
	 }

  static public BookStatus getBookStatus(int bookStatusCode) {
      for (BookStatus bookStatus: BookStatus.values()) {
          if (bookStatus.getBookStatusCode()==(bookStatusCode)) {
              return bookStatus;
          }
      }
      throw new RuntimeException("unknown type");
  }

  static public BookStatus getBookStatus(String bookStatusCode) {
      for (BookStatus bookStatus: BookStatus.values()) {
          if (bookStatus.getBookStatusCode()==(Integer.parseInt(bookStatusCode))) {
              return bookStatus;
          }
      }
      throw new RuntimeException("unknown type");
  }

	 public int getBookStatusCode() {
	   return bookStatusCode;
	 }

}
