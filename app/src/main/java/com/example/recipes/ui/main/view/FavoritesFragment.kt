package com.example.recipes.ui.main.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recipes.R
import com.example.recipes.data.model.Recipes
import com.example.recipes.ui.main.viewmodel.FavoritesViewModel
import com.example.recipes.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private lateinit var recipeTitle: TextView
    private lateinit var recipePhoto: ImageView
    private lateinit var summary: TextView
    private lateinit var instructions: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeTitle = view.findViewById(R.id.recipe_title)
        recipePhoto = view.findViewById(R.id.recipe_photo)
        summary = view.findViewById(R.id.summary)
        instructions = view.findViewById(R.id.instructions)
        setupObserver()
    }

    private fun setupObserver() {
        favoritesViewModel.recipes.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { recipes -> setupViews(recipes) }
                Status.ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun setupViews(recipes: Recipes) {
        val recipe = recipes.recipes.findLast { it.veryPopular }
        if (recipe != null) {
            Glide.with(this)
                .load(recipe.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(recipePhoto)

            recipeTitle.text = recipe.title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                summary.text = Html.fromHtml(recipe.summary, Html.FROM_HTML_MODE_COMPACT)
                instructions.text = Html.fromHtml(recipe.instructions, Html.FROM_HTML_MODE_COMPACT)
            }
        }
    }
}

