package com.revature.DAOs;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDAOInterface {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User createUser(User user) throws SQLException;
    public boolean deleteUser(int id) throws SQLException;
    public User updateUser(User user) throws SQLException;
}
