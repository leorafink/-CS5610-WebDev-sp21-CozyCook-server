package com.example.cozycook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cozycook.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

/*  @Query(value = "SELECT * FROM users WHERE topic_id = :tid",
          nativeQuery = true)
  public List<User> findWidgetsForTopic(@Param("tid") String topicId);*/

  @Query(value = "SELECT * FROM users",
          nativeQuery = true)
  public List<User> findAllUsers();

  @Query(value = "SELECT * FROM users WHERE id = :uid",
          nativeQuery = true)
  public User findUserById(@Param("uid") Long userId);

  @Query(value = "SELECT * FROM users WHERE username = :username",
          nativeQuery = true)
  public User findUserByUsername(@Param("username") String username);
}