package com.example.culinarycompanion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.culinarycompanion.data.Recipe
import com.example.culinarycompanion.data.RecipeDatabase

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    fun insert(recipe: Recipe) {
        Thread { recipeDao.insert(recipe) }.start()
    }

    fun update(recipe: Recipe) {
        Thread { recipeDao.update(recipe) }.start()
    }

    fun delete(recipe: Recipe) {
        Thread { recipeDao.delete(recipe) }.start()
    }
}
