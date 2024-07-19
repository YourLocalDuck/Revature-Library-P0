package com.revature.DAOs;

import java.util.List;

import com.revature.models.Book;

public interface BookDAOInterface {
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    public List<Book> getBooksByUser(int user_id);
    public Book createBook(Book book);
    public Book updateBook(Book book);
    public boolean deleteBook(int id);
    public boolean returnBook(int id);
}
