package com.example.cozycook.controllers;

import java.util.List;

import com.example.cozycook.models.Recipe;
import com.example.cozycook.models.User;
import com.example.cozycook.services.RecipeService;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RecipeController {
  @Autowired
  RecipeService service;

  @PostMapping("/api/users/{userId}/recipes")
  public Recipe createRecipeForUser(
          @PathVariable("userId") Long userId,
          @RequestBody Recipe recipe) {
    System.out.println("userId is: " + userId);
    System.out.println("recipe's original ID is: " + recipe.getOriginalId());
    recipe.setUserId(userId);
    System.out.println("afte added t daatbase: " + service.createRecipeForUser(recipe).getOriginalId());
    return service.createRecipeForUser(recipe);
  }

  @GetMapping("/api/users/{userId}/recipes")
  public List<Recipe> findAllRecipesForUser(@PathVariable("userId") Long userId) {
    System.out.println("userId in server recipe controller is: " + userId);
    return this.service.findAllRecipesForUser(userId);
  }

  @DeleteMapping("/api/users/{userId}/recipes/{recipeId}")
  public int deleteRecipe(@PathVariable("recipeId") Long recipeId) {
    System.out.println("recipe id being deleted: " + recipeId);
    this.service.deleteRecipe(recipeId);
    return 1;
  }

  @GetMapping("/api/users/{userId}/recipes/{recipeId}")
  public Recipe findRecipeById(@PathVariable("recipeId") Long recipeId) {
    return this.service.findRecipeById(recipeId);
  }

  @PutMapping("/api/users/{userId}/recipes/{recipeId}")
  public void updateRecipe(@PathVariable("recipeId") Long recipeId, @RequestBody Recipe recipe) {
    Long id = (Long)recipeId;
    this.service.updateRecipe(id, recipe);
  }

  @GetMapping("/api/users/{userId}/mostRecentRecipe")
  public Recipe findMostRecentRecipe(@PathVariable("userId") Long userId) {
    return this.service.findMostRecentRecipe(userId);
  }

  @GetMapping("/api/recipes/{recipeId}")
  public List<User> findUsersWhoLikeThisRecipe(@PathVariable("recipeId") Long recipeId) {
    return this.service.findUsersWhoLikeThisRecipe(recipeId);
  }
}