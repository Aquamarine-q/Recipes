package com.example.recipes.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recipes.R
import com.example.recipes.data.model.Recipe
import com.example.recipes.data.model.Recipes
import com.example.recipes.ui.main.viewmodel.MostPopularRecipeViewModel
import com.example.recipes.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MostPopularRecipeFragment : Fragment() {

    private val mostPopularRecipeViewModel: MostPopularRecipeViewModel by viewModel()
    private lateinit var hotRecipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        val imageView = view.findViewById<ImageView>(R.id.most_popular_recipe_photo)
        val textView = view.findViewById<TextView>(R.id.recipe_title)
        /*textView.text = hotRecipe.title
        Glide.with(this)
            .load(hotRecipe.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)*/
    }

    private fun setupObserver() {
        mostPopularRecipeViewModel.recipes.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { recipes -> getMostPopularRecipe(recipes) }
                Status.ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getMostPopularRecipe(recipes: Recipes) {
        //hotRecipe = recipes.recipes[0]
//        Log.d("test", recipes.recipes[0].toString())
    }
}