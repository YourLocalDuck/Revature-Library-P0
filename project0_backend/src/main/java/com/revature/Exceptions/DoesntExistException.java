package com.revature.Exceptions;

import java.sql.SQLException;

/**
 * UserDoesntExistException
 */
public class DoesntExistException extends SQLException{
    public DoesntExistException() {
        super("User doesnt exist");
    }
    
}