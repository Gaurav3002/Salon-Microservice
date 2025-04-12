package com.adsalon.service;

import com.adsalon.Exception.UserException;
import com.adsalon.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(long id) throws UserException;
    List<User> getAllUsers();
    void deleteUser(long id) throws UserException;
    User updateUser(long id, User user) throws UserException;
}
