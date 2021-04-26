package com.example.cozycook.repositories;

import java.util.List;

import com.example.cozycook.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cozycook.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

  @Query(value="SELECT * FROM recipes WHERE user_id=:uid", nativeQuery = true)
  public List<Recipe> findAllRecipesForUser(@Param("uid")Long userId);

  @Query(value="SELECT * FROM recipes WHERE user_id=:uid ORDER BY id DESC LIMIT 1", nativeQuery = true)
  public Recipe findMostRecentRecipe(@Param("uid") Long userId);

  @Query(value="SELECT username FROM users, recipes WHERE recipes.user_id=users.id AND recipes.original_id=:recipeId", nativeQuery = true)
  public List<String> findUsersWhoLikeThisRecipe(@Param("recipeId") String recipeId);

 // @Query(value="SELECT users.username FROM users, recipes WHERE recipes.user_id=users.id AND recipes.id=:recipeId")

 // @Query(value="SELECT username FROM users LEFT JOIN recipes ON users.id == recipes.user_id WHERE user_id=:uid")
}