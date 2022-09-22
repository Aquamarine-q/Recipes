package com.example.recipes.ui.main.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recipes.data.model.Recipe
import com.example.recipes.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeTitle = binding.recipeTitle
        val recipePhoto = binding.recipePhoto
        val ingredients = binding.ingredients
        val summary = binding.summary
        val instructions = binding.instructions

        val recipe = arguments?.getParcelable<Recipe>("recipe")
        if (recipe != null) {
            Glide.with(this)
                .load(recipe.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(recipePhoto)

            recipeTitle.text = recipe.title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                summary.text = Html.fromHtml(recipe.summary, Html.FROM_HTML_MODE_COMPACT)
            }
            var str = ""
            recipe.extendedIngredients.forEach {
                str += "· " + it.name + "  ${it.amount} " + it.unit + "\n"
            }
            ingredients.text = str

            var s1 = ""
            var i = 1

            recipe.analyzedInstructions.forEach { s ->
                s.steps.forEach {
                    s1 += "${i++}. " + "${it.step}" + "\n\n"
                }
            }
            instructions.text = s1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}