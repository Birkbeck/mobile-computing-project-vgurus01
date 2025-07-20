package com.example.culinarycompanion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class ViewRecipe : AppCompatActivity() {
    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        val recipeId = intent.getIntExtra("recipe_id", -1)

        viewModel.allRecipes.observe(this) { recipes ->
            val recipe = recipes.find { it.id == recipeId }
            recipe?.let {
                findViewById<TextView>(R.id.recipeName).text = it.name
                findViewById<TextView>(R.id.categoryName).text = "Category: ${it.category}"
                findViewById<TextView>(R.id.instructionsHeading).text = "Instructions:\n${it.instructions}"
                // Populate ingredients list if needed
            }
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(this, EditRecipe::class.java)
            intent.putExtra("recipe_id", recipeId)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}
