package com.example.recipes.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.model.Recipes
import com.example.recipes.ui.main.adapter.RecipeAdapter
import com.example.recipes.ui.main.viewmodel.MainViewModel
import com.example.recipes.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        adapter = RecipeAdapter(arrayListOf())
        recyclerView.adapter = adapter

        setupObserver()
        /*val apiInterface = ApiInterface.create()
            .getRecipes(getString(R.string.api_key), 10)

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                val recipes = response.body()!!.recipes
                val recipesTitle = recipes.map { it.title }
                adapter = RecipeAdapter(recipes)
                recyclerView.adapter = adapter

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        var filteredList = recipes
                        for(recipe in recipes) {
                            if (recipesTitle.contains(newText)) {
                                //filteredList.add(recipe)
                            }
                        }
                        return false
                    }

                })



                /*val addToFavoritesButton = view.findViewById<Button>(R.id.add_to_favorites_button)
                addToFavoritesButton.setOnClickListener{
                    it.background = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_baseline_favorite_24)
                }*/
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Log.d("test", "gg ${t.message}")
            }
        })*/
        adapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val navHostFragment = requireActivity()
                    .supportFragmentManager
                    .findFragmentById(R.id.fragment_container) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_home_to_recipe)
            }
        })
    }

    private fun setupObserver() {
        mainViewModel.recipes.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { recipes -> renderList(recipes) }
                Status.ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun renderList(recipes: Recipes) {
        adapter.addData(recipes)
        adapter.notifyDataSetChanged()
    }
}
