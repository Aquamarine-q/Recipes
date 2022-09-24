package com.example.recipes.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.recipes.R
import com.example.recipes.data.model.Recipes
import com.example.recipes.databinding.FragmentFavoritesBinding
import com.example.recipes.ui.main.adapter.RecipeAdapter
import com.example.recipes.ui.main.viewmodel.FavoritesViewModel
import com.example.recipes.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView

        adapter = RecipeAdapter(arrayListOf())
        recyclerView.adapter = adapter

        setupObserver()
    }

    private fun setupObserver() {
        favoritesViewModel.recipes.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { recipes -> renderList(recipes) }
                Status.ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun renderList(recipes: Recipes) {
        val favoriteRecipes = recipes.recipes.filter { it.veryPopular }
        adapter.setData(favoriteRecipes)
        adapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val navHostFragment = requireActivity()
                    .supportFragmentManager
                    .findFragmentById(R.id.fragment_container) as NavHostFragment
                val navController = navHostFragment.navController
                val bundle = bundleOf("recipe" to favoriteRecipes[position])
                navController.navigate(R.id.action_favorites_to_recipe, bundle)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

