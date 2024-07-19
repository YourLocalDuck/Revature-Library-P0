package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Exceptions.DoesntExistException;
import com.revature.models.Book;
import com.revature.utils.ConnectionUtil;

public class BookDAO implements BookDAOInterface {

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<Book>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Book book = new Book(result.getInt("book_id"), result.getString("book_title"), result.getString("book_author"), result.getInt("book_rating"), result.getBoolean("checked_out"), new UserDAO().getUserById(result.getInt("checked_out_by_fk")));

                allBooks.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return allBooks;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books WHERE book_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                book = new Book(result.getInt("book_id"), result.getString("book_title"), result.getString("book_author"), result.getInt("book_rating"), result.getBoolean("checked_out"), new UserDAO().getUserById(result.getInt("checked_out_by_fk")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return book;
    }

    @Override
    public List<Book> getBooksByUser(int user_id){
        List<Book> allBooks = new ArrayList<Book>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books WHERE checked_out_by_fk = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Book book = new Book(result.getInt("book_id"), result.getString("book_title"), result.getString("book_author"), result.getInt("book_rating"), result.getBoolean("checked_out"), new UserDAO().getUserById(result.getInt("checked_out_by_fk")));

                allBooks.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return allBooks;
    }

    @Override
    public Book createBook(Book book) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO books (book_title, book_author, book_rating, checked_out, checked_out_by_fk) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getBook_title());
            statement.setString(2, book.getBook_author());
            statement.setInt(3, book.getBook_rating());
            statement.setBoolean(4, book.isChecked_out());
            statement.setInt(5, book.getChecked_out_by_fk());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            int book_id = 0;
            while(result.next()) {
                book_id = result.getInt("book_id");
            }
            book.setBook_id(book_id);

            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book updateBook(Book book) throws SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE books SET book_title = ?, book_author = ?, book_rating = ?, checked_out = ?, checked_out_by_fk = ? WHERE book_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, book.getBook_title());
            statement.setString(2, book.getBook_author());
            statement.setInt(3, book.getBook_rating());
            statement.setBoolean(4, book.isChecked_out());
            statement.setInt(5, book.getChecked_out_by_fk());
            statement.setInt(6, book.getBook_id());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0)
                return book;
            else
                throw new DoesntExistException();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean deleteBook(int id) throws SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM books WHERE book_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0)
                return true;
            else
                throw new DoesntExistException();
        } catch (SQLException e) {
            throw e;
        }

    }

    @Override
    public boolean returnBook(int id) throws SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE books SET checked_out = false, checked_out_by_fk = 0 WHERE book_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0)
                return true;
            else
                throw new DoesntExistException();
        } catch (SQLException e) {
            throw e;
        }
    }

}
