package com.example.culinarycompanion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycompanion.data.Recipe

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var recyclerView: RecyclerView
    private var recipeList = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allRecipes.observe(this) { recipes ->
            recipeList = recipes.toMutableList()
            recyclerView.adapter = RecipeAdapter(recipes) { selectedRecipe ->
                val intent = Intent(this, ViewRecipe::class.java)
                intent.putExtra("recipe_id", selectedRecipe.id)
                startActivity(intent)
            }
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            startActivity(Intent(this, CreateRecipe::class.java))
        }
    }
}
