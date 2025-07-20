package com.example.culinarycompanion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycompanion.data.Recipe

class CreateRecipe : AppCompatActivity() {
    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        val saveButton = findViewById<Button>(R.id.saveButton)
        val nameField = findViewById<EditText>(R.id.recipeName)
        val spinner = findViewById<Spinner>(R.id.spinner)

        saveButton.setOnClickListener {
            val name = nameField.text.toString()
            val category = spinner.selectedItem.toString()
            val recipe = Recipe(name = name, category = category, ingredients = "", instructions = "")
            viewModel.insert(recipe)
            finish()
        }
    }
}