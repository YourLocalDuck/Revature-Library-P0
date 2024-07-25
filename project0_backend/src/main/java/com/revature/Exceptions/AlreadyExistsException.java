package com.revature.Exceptions;

import java.sql.SQLException;

public class AlreadyExistsException extends SQLException {
    public AlreadyExistsException() {
        super("The entity already exists in the database.");
    }
    
}
