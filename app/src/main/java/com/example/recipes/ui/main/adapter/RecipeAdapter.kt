package com.example.recipes.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recipes.R
import com.example.recipes.data.model.Recipe
import com.example.recipes.utils.RecipeDiffUtil

class RecipeAdapter(
    private var dataset: ArrayList<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {
    private lateinit var mListener: OnItemClickListener

    class ItemViewHolder(view: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val recipeName: TextView = view.findViewById(R.id.recipe_name)
        val imageView: ImageView = view.findViewById(R.id.dish_photo)

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout, mListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.recipeName.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)
    }

    override fun getItemCount() = dataset.size

    fun setData(newRecipeList: List<Recipe>) {
        val diffUtil = RecipeDiffUtil(dataset, newRecipeList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        dataset = newRecipeList as ArrayList<Recipe>
        diffResult.dispatchUpdatesTo(this)
    }
}