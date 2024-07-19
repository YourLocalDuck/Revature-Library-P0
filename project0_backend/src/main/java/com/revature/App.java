package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.BookController;
import com.revature.controllers.UserController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class App 
{
    public static void main( String[] args )
    {
        try(Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Connection successful");
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }

        var app = Javalin.create().start(3000).get("/", ctx -> ctx.result("Root"));

        UserController uc = new UserController();
        BookController bc = new BookController();

        app.get("/users", uc.getAllUsersHandler);
        app.get("/users/{id}", uc.getUserByIdHandler);
        app.post("/users", uc.createUserHandler);
        app.put("/users/{id}", uc.updateUserHandler);
        app.delete("/users/{id}", uc.deleteUserHandler);

        app.get("/books", bc.getAllBooksHandler);
        app.get("/books/{id}", bc.getBookByIdHandler);
        app.post("/books", bc.createBookHandler);
        app.put("/books/{id}", bc.updateBookHandler);
        app.delete("/books/{id}", bc.deleteBookHandler);
        app.post("/books/return/{id}", bc.returnBookHandler);
        app.get("/books/user/{user_id}", bc.getBookByUserHandler);
    }
}
