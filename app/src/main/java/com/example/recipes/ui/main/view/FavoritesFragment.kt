package com.example.recipes.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipes.R

class FavoritesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val myDataset = Datasource().loadContacts()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RecipeAdapter(myDataset)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {

            }
        })*/
    }
}