package com.example.culinarycompanion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycompanion.data.Recipe

class EditRecipe : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel
    private var editingRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        val recipeId = intent.getIntExtra("recipe_id", -1)

        val nameInput = findViewById<EditText>(R.id.recipeName)
        val categorySpinner = findViewById<Spinner>(R.id.spinner)

        recipeViewModel.allRecipes.observe(this) { recipes ->
            editingRecipe = recipes.find { it.id == recipeId }
            editingRecipe?.let {
                nameInput.setText(it.name)
            }
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            editingRecipe?.let { original ->
                val updated = original.copy(
                    name = nameInput.text.toString(),
                    category = categorySpinner.selectedItem.toString()
                )
                recipeViewModel.modifyRecipe(updated)
                finish()
            }
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            editingRecipe?.let {
                recipeViewModel.removeRecipe(it)
                finish()
            }
        }

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}