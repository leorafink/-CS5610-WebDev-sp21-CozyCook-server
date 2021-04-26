package com.example.cozycook.controllers;

import java.util.List;

import com.example.cozycook.models.User;
import com.example.cozycook.services.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;


@RestController
// Local: @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@CrossOrigin(origins = "http://cs5610-charlotteswebdev-client.herokuapp.com", allowCredentials = "true")
public class UserController {
  @Autowired
  UserService service;


  @PostMapping("/api/register")
  public User register(@RequestBody User newUser, HttpSession session) {
    User existingUser = service.findUserByUsername(newUser.getUsername());
    if(existingUser == null) {
      newUser = service.createUser(newUser);
      session.setAttribute("profile", newUser);
      return newUser;
    }
    return null;
  }

  @GetMapping("/api/home/mostRecentUser")
  public User findMostRecentUser() {
    return service.findMostRecentUser();
  }

//  @PutMapping("/api/users/{uid}")
//  public int updateUser(@PathVariable("uid") Long uid,
//                        @RequestBody User user) {
//    return this.service.updateUser(uid, user);
//  }

@DeleteMapping("/api/users/{uid}")
public int deleteUser(@PathVariable("uid") Long uid) {
  return this.service.deleteUser(uid);
}

@GetMapping("/api/users")
public List<User> findAllUsers() {
    return this.service.findAllUsers();
}

  @GetMapping("/api/profile")
  public User privateProfile(HttpSession session) {
    System.out.println("Current profile: " + session.getAttribute("profile"));
    if (session.getAttribute("profile") != null) {
      return (User) session.getAttribute("profile");
    }
    return null;
  }

  @GetMapping("/api/profile/{userId}")
  public User publicProfile(@PathVariable("userId") Long userId) {
    return  service.findUserById(userId);
  }

  @GetMapping("/api/users/{username}")
  public User findUserByUsername(@PathVariable("username") String username) {
      return service.findUserByUsername(username);
  }


  @GetMapping("/api/users/{username}/{password}")
  public User findUsernameByCredentials(@PathVariable("username") String username, @PathVariable("password") String password) {
    return service.findUserByCredentials(username, password);
  }

  @PostMapping("/api/login")
  public User login(@RequestBody User credentials, HttpSession session) throws Exception {
    User existingUser = service.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
    if (existingUser != null) {
      session.setAttribute("profile", existingUser);
      return existingUser;
//    } else {
//      throw new Error("user profile does not exist");
    }
    return null;
  }

  @GetMapping("/api/session")
  public User getSession(HttpSession session) {
    System.out.println((User)session.getAttribute("profile"));
    return (User)session.getAttribute("profile");
  }

  @GetMapping("/api/logout")
  public void logout(HttpSession session) {
    session.invalidate();
  }

  @PutMapping("/api/users/{userId}")
  public void updateUser(@PathVariable("userId") Long userId, @RequestBody User user/*, HttpSession session*/) {
    /*User existingUser = service.findUserByUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    existingUser.setEmail(user.getEmail());
    existingUser.setRole(user.getRole());
    session.setAttribute("profile", existingUser);
    return existingUser;*/
    Long id = (Long)userId;
    this.service.updateUser(id, user);
  }

}