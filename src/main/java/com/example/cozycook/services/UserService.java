package com.example.cozycook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.example.cozycook.models.User;
import com.example.cozycook.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  public User createUser(Long uid, User user) {
    /*
    Long id = (new Date()).getTime();
    widget.setId(id);
    widgets.add(widget);
    return widget;
    */

    return repository.save(user);
  }

  /*public List<User> findWidgetsForTopic(String tid) {
    *//*
    List<Widget> ws = new ArrayList<Widget>();
    for (Widget w : widgets) {
      if (w.getTopicId().equals(tid)) {
        ws.add(w);
      }
    }
    return ws;
    *//*

    return repository.findWidgetsForTopic(tid);
  }*/

  public int updateUser(Long uid, User user) {
    /*
    for (int i = 0 ; i < widgets.size() ; i++) {
      Widget w = widgets.get(i);
      if (w.getId().equals(wid)) {
        widgets.set(i, widget);
        return 1;
      }
    }
    return 0;
    */

    User originalUser = findUserById(uid);

    if (user.getUsername() != null) {
      originalUser.setUsername(user.getUsername());
    }
    if (user.getId() != null) {
      originalUser.setId(user.getId());
    }
    if (user.getPassword() != null) {
      originalUser.setPassword(user.getPassword());
    }

    repository.save(originalUser);
    return 1;
  }

  public int deleteUser(Long uid) {
    /*
    int index = -1;
    for (int i = 0; i < widgets.size(); i++) {
      Widget w = widgets.get(i);
      if (w.getId().equals(wid)) {
        index = i;
      }
    }
    if (index >= 0) {
      widgets.remove(index);
      return 1;
    }
    return 0;
    */

    repository.deleteById(uid);
    return 1;
  }

  public List<User> findAllWidgets() {
    /*
    return widgets;
    */
    // return (List<Widget>)repository.findAll();

    return repository.findAllUsers();
  }

  public User findUserById(Long uid) {
    /*
    for (Widget w : widgets) {
      if (w.getId().equals(wid)) {
        return w;
      }
    }
    return null;
    */
    // return repository.findById(wid).get();

    return repository.findUserById(uid);
  }
}