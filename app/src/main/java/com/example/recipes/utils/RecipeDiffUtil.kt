package com.example.recipes.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.recipes.data.model.Recipe

class RecipeDiffUtil(private val oldList: List<Recipe>, private val newList: List<Recipe>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].summary != newList[newItemPosition].summary -> false
            oldList[oldItemPosition].instructions != newList[newItemPosition].instructions -> false
            else -> true
        }
    }
}