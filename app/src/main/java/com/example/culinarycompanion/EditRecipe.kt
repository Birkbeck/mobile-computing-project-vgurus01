package com.example.culinarycompanion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.culinarycompanion.data.Recipe
import androidx.lifecycle.ViewModelProvider

class EditRecipe : AppCompatActivity() {

    private lateinit var viewModel: RecipeViewModel
    private var currentRecipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        val id = intent.getIntExtra("recipe_id", -1)
        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        val nameField = findViewById<EditText>(R.id.recipeName)
        val spinner = findViewById<Spinner>(R.id.spinner)

        viewModel.allRecipes.observe(this) { recipes ->
            val recipe = recipes.find { it.id == id }
            recipe?.let {
                currentRecipe = it
                nameField.setText(it.name)
                // Set spinner selection if needed
                // Load ingredients/instructions if using extra fields
            }
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val updated = currentRecipe?.copy(
                name = nameField.text.toString(),
                category = spinner.selectedItem.toString()
            )
            if (updated != null) {
                viewModel.update(updated)
                finish()
            }
        }

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            currentRecipe?.let {
                viewModel.delete(it)
                finish()
            }
        }

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}
