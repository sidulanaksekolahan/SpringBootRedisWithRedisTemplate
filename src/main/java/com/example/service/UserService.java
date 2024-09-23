package com.example.service;

import com.example.model.User;
import com.example.model.UserDto;

import java.util.List;

public interface UserService {

    User saveUser(UserDto userDto);

    User getUserById(String id);

    List<User> getAllUsers();

    void deleteUserById(String id);

    User updateUser(String id, UserDto userDto);
}
