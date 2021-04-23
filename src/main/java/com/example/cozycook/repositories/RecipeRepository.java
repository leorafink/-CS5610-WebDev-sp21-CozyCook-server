package com.example.cozycook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cozycook.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

  @Query(value="SELECT * FROM recipes WHERE user_id=:uid", nativeQuery = true)
  public List<Recipe> findAllRecipesForUser(@Param("uid")Long userId);

}