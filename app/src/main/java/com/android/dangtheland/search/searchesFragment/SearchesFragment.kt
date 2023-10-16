package com.android.dangtheland.search.searchesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.dangtheland.databinding.FragmentSearchesBinding
import com.android.dangtheland.search.searchesFragment.searchesAdapter.SearchesAdapter
import com.android.dangtheland.search.searchesFragment.searchesItemModel.SearchesData


class SearchesFragment : Fragment() {

    private val binding by lazy { FragmentSearchesBinding.inflate(layoutInflater)}

    private var searchesList = mutableListOf<SearchesData>()

    private var searchesAdapter = SearchesAdapter(searchesList)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewSearches.apply {
            adapter = searchesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    fun searchesData(list: MutableList<SearchesData>){
        searchesAdapter.searchesData(list)
    }
    companion object {
        fun newInstance() = SearchesFragment()
    }
}