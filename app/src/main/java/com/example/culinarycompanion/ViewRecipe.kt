package com.example.culinarycompanion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ViewRecipe : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        val recipeId = intent.getIntExtra("recipe_id", -1)

        recipeViewModel.allRecipes.observe(this) { recipes ->
            recipes.find { it.id == recipeId }?.let { recipe ->
                findViewById<TextView>(R.id.recipeName).text = recipe.name
                findViewById<TextView>(R.id.categoryName).text = "Category: ${recipe.category}"
                findViewById<TextView>(R.id.instructionsHeading).text = "Instructions:\n${recipe.instructions}"
            }
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            Intent(this, EditRecipe::class.java).apply {
                putExtra("recipe_id", recipeId)
                startActivity(this)
            }
        }

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}