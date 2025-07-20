package com.example.culinarycompanion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycompanion.data.Recipe

class CreateRecipe : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        val saveBtn = findViewById<Button>(R.id.saveButton)
        val nameInput = findViewById<EditText>(R.id.recipeName)
        val categorySpinner = findViewById<Spinner>(R.id.spinner)

        saveBtn.setOnClickListener {
            val newRecipe = Recipe(
                name = nameInput.text.toString(),
                category = categorySpinner.selectedItem.toString(),
                ingredients = "",
                instructions = ""
            )
            recipeViewModel.addRecipe(newRecipe)
            finish()
        }
    }
}