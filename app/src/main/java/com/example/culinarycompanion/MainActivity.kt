package com.example.culinarycompanion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        val recipeRecycler = findViewById<RecyclerView>(R.id.recyclerView)
        recipeRecycler.layoutManager = LinearLayoutManager(this)

        recipeViewModel.allRecipes.observe(this) { recipes ->
            recipeRecycler.adapter = RecipeAdapter(recipes) { recipe ->
                startActivity(Intent(this, ViewRecipe::class.java).apply {
                    putExtra("recipe_id", recipe.id)
                }
            }
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            startActivity(Intent(this, CreateRecipe::class.java))
        }
    }
}

