package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.Exceptions.AlreadyExistsException;
import com.revature.Exceptions.DoesntExistException;
import com.revature.Exceptions.UserHasBooksCheckedOutException;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
    UserDAO userDAO = new UserDAO();

    public Handler getAllUsersHandler = (ctx) -> {
        ctx.json(userDAO.getAllUsers());
        ctx.status(200);
    };

    public Handler getUserByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = userDAO.getUserById(id);

        if (user != null) {
            ctx.json(user);
            ctx.status(200);
        } else {
            ctx.json("User doesn't exist");
            ctx.status(400);
        }
    };

    public Handler createUserHandler = (ctx) -> {
        User user = ctx.bodyAsClass(com.revature.models.User.class);
        try {
            User insertedUser = userDAO.createUser(user);

            if (insertedUser != null) {
                ctx.json(insertedUser);
                ctx.status(201);
            } else {
                ctx.json("Invalid Input");
                ctx.status(400);
            }
        } catch (AlreadyExistsException e) {
            ctx.json("User already exists");
            ctx.status(400);
        } catch (Exception e) {
            ctx.json("Invalid Request");
            ctx.status(400);
        }
    };

    public Handler updateUserHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = ctx.bodyAsClass(com.revature.models.User.class);
        user.setUser_id(id);
        try {
            User updatedUser = userDAO.updateUser(user);
            if (updatedUser != null) {
                ctx.json(updatedUser);
                ctx.status(200);
            } else {
                ctx.json("Invalid Input");
                ctx.status(400);
            }
        } catch (DoesntExistException e) {
            ctx.json("User Doesn't exist");
            ctx.status(400);
        } catch (Exception e) {
            ctx.json("Invalid Request");
            ctx.status(400);
        }
    };

    public Handler deleteUserHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            boolean deleted = userDAO.deleteUser(id);
            if (deleted) {
                ctx.status(204);
            } else {
                ctx.json("Invalid Request");
                ctx.status(400);
            }
        } catch (DoesntExistException e) {
            ctx.json("User Doesn't exist");
            ctx.status(400);
        } catch (UserHasBooksCheckedOutException e) {
            ctx.json("User has books checked out");
            ctx.status(400);
        } catch (Exception e) {
            ctx.json("Invalid Request");
            ctx.status(400);
        }
    };
}
