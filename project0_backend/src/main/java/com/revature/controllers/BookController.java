package com.revature.controllers;

import com.revature.DAOs.BookDAO;
import com.revature.models.Book;

import io.javalin.http.Handler;

public class BookController {
    BookDAO bookDAO = new BookDAO();
    
    public Handler getAllBooksHandler = (ctx) -> {
        ctx.json(bookDAO.getAllBooks());
    };

    public Handler getBookByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Book book = bookDAO.getBookById(id);
        if (book != null) {
            ctx.json(bookDAO.getBookById(id));
            ctx.status(200);
        } else {
            ctx.json("Book doesn't exist");
            ctx.status(400);
        }
        
    };

    public Handler getBookByUserHandler = (ctx) -> {
        int user_id = Integer.parseInt(ctx.pathParam("user_id"));
        ctx.json(bookDAO.getBooksByUser(user_id));
    };

    public Handler createBookHandler = (ctx) -> {
        Book book = ctx.bodyAsClass(com.revature.models.Book.class);
        Book insertedBook = bookDAO.createBook(book);
        if (insertedBook != null) {
            ctx.json(insertedBook);
            ctx.status(201);
        } else {
            ctx.json("Invalid Input");
            ctx.status(400);
        }
    };

    public Handler updateBookHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Book book = ctx.bodyAsClass(com.revature.models.Book.class);
        book.setBook_id(id);
        Book updatedBook = bookDAO.updateBook(book);
        if (updatedBook != null) {
            ctx.json(updatedBook);
            ctx.status(200);
        } else {
            ctx.json("Invalid Input");
            ctx.status(400);
        }
    };

    public Handler deleteBookHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = bookDAO.deleteBook(id);
        if (deleted) {
            ctx.status(204);
        } else {
            ctx.json("Invalid Input");
            ctx.status(400);
        }
    };

    public Handler returnBookHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean returned = bookDAO.returnBook(id);
        if (returned) {
            ctx.status(200);
        } else {
            ctx.json("Invalid Input");
            ctx.status(400);
        }
    };
}
