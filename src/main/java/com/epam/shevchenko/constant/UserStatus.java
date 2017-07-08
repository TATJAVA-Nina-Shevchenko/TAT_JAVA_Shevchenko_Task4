package com.epam.shevchenko.constant;

public enum UserStatus {
	COMMON(1), USER(2), ADMIN(3), SUPER_ADMIN(4), BANNED(5);
	
	 private int userStatusCode;

	 UserStatus(int code) {
	   this.userStatusCode = code;
	 }

   static public UserStatus getUserStatus(int userStatusCode) {
       for (UserStatus userStatus: UserStatus.values()) {
           if (userStatus.getUserStatusCode()==(userStatusCode)) {
               return userStatus;
           }
       }
       throw new RuntimeException("unknown type");
   }

   static public UserStatus getUserStatus(String userStatusCode) {
       for (UserStatus userStatus: UserStatus.values()) {
           if (userStatus.getUserStatusCode()==(Integer.parseInt(userStatusCode))) {
               return userStatus;
           }
       }
       throw new RuntimeException("unknown type");
   }

	 public int getUserStatusCode() {
	   return userStatusCode;
	 }

}
