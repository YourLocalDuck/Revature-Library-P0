package com.revature.DAOs;

import java.util.List;

import com.revature.models.User;

public interface UserDAOInterface {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User createUser(User user);
    public boolean deleteUser(int id);
    public User updateUser(User user);
}
