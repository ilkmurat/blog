package com.blog.ilkmurat.controller;

import com.blog.ilkmurat.model.User;
import com.blog.ilkmurat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //---Retrieve all users---//
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    //----Retrieve single user ---//
    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("user_id") String user_id) {
        User user = userRepository.findByID(user_id);
        if(user!=null){
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //----Create a user ----//
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());

        if (userRepository.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userRepository.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<User> deleteUser(@PathVariable("user_id") String user_id) {

        User user = userRepository.findByID(user_id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + user_id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteUserById(user_id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //----update a user ----//
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser( @RequestBody User user) {


        if (userRepository.isUserExist(user)) {
            userRepository.updateUser(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
    }


    //---login process----//
    @GetMapping("/user/authenticate/{userName}/{password}")
    public User login(@PathVariable("userName") String userName , @PathVariable("password") String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        return userRepository.authenticateUser(user);
    }

}
