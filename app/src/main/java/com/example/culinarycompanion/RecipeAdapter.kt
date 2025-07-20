package com.example.culinarycompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycompanion.data.Recipe

class RecipeAdapter(
    private val recipeList: List<Recipe>,
    private val clickListener: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {

    inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.tvTitle)

        fun setupWith(recipe: Recipe) {
            titleView.text = recipe.name
            itemView.setOnClickListener { clickListener(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recipe, parent, false)
        return RecipeHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.setupWith(recipeList[position])
    }

    override fun getItemCount() = recipeList.size
}