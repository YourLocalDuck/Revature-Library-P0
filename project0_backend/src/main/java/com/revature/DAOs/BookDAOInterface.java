package com.revature.DAOs;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Book;

public interface BookDAOInterface {
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    public List<Book> getBooksByUser(int user_id);
    public Book createBook(Book book) throws SQLException;
    public Book updateBook(Book book) throws SQLException;
    public boolean deleteBook(int id) throws SQLException;
    public boolean returnBook(int id) throws SQLException;
}
