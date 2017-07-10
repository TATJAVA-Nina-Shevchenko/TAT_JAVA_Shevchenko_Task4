package com.epam.shevchenko.constant;

public enum OrderStatus {
	
	RETURNED(1), RESERVED(2), LEASED(3), REFUSED(4),

	//
	;
	
	 private int orderStatusCode;

	 OrderStatus(int code) {
	   this.orderStatusCode = code;
	 }

  static public OrderStatus getBookStatus(int orderStatusCode) {
      for (OrderStatus orderStatus: OrderStatus.values()) {
          if (orderStatus.getOrderStatusCode()==(orderStatusCode)) {
              return orderStatus;
          }
      }
      throw new RuntimeException("unknown type");
  }

  static public OrderStatus getOrderStatus(String orderStatusCode) {
      for (OrderStatus orderStatus: OrderStatus.values()) {
          if (orderStatus.getOrderStatusCode()==(Integer.parseInt(orderStatusCode))) {
              return orderStatus;
          }
      }
      throw new RuntimeException("unknown type");
  }

	 public int getOrderStatusCode() {
	   return orderStatusCode;
	 }

}
