package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Exceptions.DoesntExistException;
import com.revature.Exceptions.UserHasBooksCheckedOutException;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAO implements UserDAOInterface {

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<User>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User user = new User(result.getInt("user_id"), result.getString("username"), result.getString("email"));
                allUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return allUsers;
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                user = new User(result.getInt("user_id"), result.getString("username"), result.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users (username, email) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            int userId = 0;
            while (result.next()) {
                userId = result.getInt("user_id");
            }
            user.setUser_id(userId);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User updateUser(User user) throws SQLException{
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET username = ?, email = ? WHERE user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getUser_id());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0)
                return user;
            else
                throw new DoesntExistException();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0)
                return true;
            else
                throw new DoesntExistException();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23503")) {
                throw new UserHasBooksCheckedOutException();
            } else {
                // Rethrow any other exceptions
                throw e;
            }
        }
    }
}
