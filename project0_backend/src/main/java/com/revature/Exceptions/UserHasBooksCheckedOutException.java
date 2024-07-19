package com.revature.Exceptions;

import java.sql.SQLException;

public class UserHasBooksCheckedOutException extends SQLException {
    public UserHasBooksCheckedOutException() {
        super("User has books checked out");
    }
    
}
