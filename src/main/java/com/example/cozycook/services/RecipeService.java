package com.example.cozycook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.example.cozycook.models.Recipe;
import com.example.cozycook.models.User;
import com.example.cozycook.repositories.RecipeRepository;
import com.example.cozycook.repositories.UserRepository;

@Service
public class RecipeService {

  @Autowired
  RecipeRepository repository;

  /*
  Uses RecipeRepository to create a new entry in the recipes table from the given recipe object.
   */
  public Recipe createRecipeForUser(Recipe recipe) {
    return this.repository.save(recipe); // An insert.
  }

  public Recipe findRecipeById(Long id) {
    return this.repository.findById(id).get();
  }


  /*
  Uses RecipeRepository to update an entry from recipes table by reassigning the entry with the given id.
   */
  public int updateRecipe(Long id, Recipe recipe) {
    Recipe originalRecipe = findRecipeById(id);
    if (recipe.getName() != null) {
      originalRecipe.setName(recipe.getName());
    }
    if (recipe.getNotes() != null) {
      originalRecipe.setNotes(recipe.getNotes());
    }
    if (recipe.getLink() != null) {
      originalRecipe.setLink(recipe.getLink());
    }
    this.repository.save(originalRecipe); // An update.
    return 1;
  }

  /*
  Uses RecipeRepository to delete an entry from recipes table by its id.
  */
  public int deleteRecipe(Long recipeId) {
    this.repository.deleteById(recipeId);
    return 1;
  }

  public List<Recipe> findAllRecipesForUser(Long userId) {
    return this.repository.findAllRecipesForUser(userId);
  }

  public Recipe findMostRecentRecipe(Long userId) {
    return this.repository.findMostRecentRecipe(userId);
  }
}