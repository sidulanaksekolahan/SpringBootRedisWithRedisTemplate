package com.example.service;

import com.example.handler.UserNotFoundExc;
import com.example.model.User;
import com.example.model.UserDto;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User(null, userDto.getName(), userDto.getAge());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        Optional<User> theuser = userRepository.findById(id);

        User tempuser = null;
        if (theuser.isPresent()) {
            tempuser = theuser.get();
        }

        return tempuser;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add); // Add all users to the list

        return users;
    }

    @Override
    public void deleteUserById(String id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundExc("user with id: " + id + " not found!");
        }
    }

    @Override
    public User updateUser(String id, UserDto userDto) {
        User theUser = getUserById(id);
        if (theUser == null) {
            throw new UserNotFoundExc("user with id: " + id + " not found!");
        }

        // update the data
        theUser.setName(userDto.getName());
        theUser.setAge(userDto.getAge());

        // save and return the user
        return userRepository.save(theUser);
    }
}
