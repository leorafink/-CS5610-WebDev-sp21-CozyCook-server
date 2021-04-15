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

@RestController
@CrossOrigin(origins = "*")
public class UserController {
  @Autowired
  UserService service;

  @PostMapping("/api/users")
  public User createWidget(@RequestBody User user) {
    return service.createUser(user);
  }

  /*@GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
    return service.findWidgetsForTopic(tid);
  }*/

  @PutMapping("/api/users/{uid}")
  public int updateUser(@PathVariable("uid") Long uid,
                        @RequestBody User user) {
    return service.updateUser(uid, user);
  }

  @DeleteMapping("/api/users/{uid}")
  public int deleteUser(@PathVariable("uid") Long uid) {
    return service.deleteUser(uid);
  }

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return service.findAllUsers();
  }

  @GetMapping("/api/users/{uid}")
  public User findUserById(@PathVariable("uid") Long uid) {
    return service.findUserById(uid);
  }

}