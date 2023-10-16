package com.android.dangtheland.search.searchesFragment.searchesViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.dangtheland.search.searchesFragment.searchesItemModel.SearchesData

class SearchesViewModel : ViewModel()  {
    val searchesList = MutableLiveData<List<SearchesData>?>()

    fun searches(list: MutableList<SearchesData>){
        val currentList = searchesList.value?.toMutableList() ?: mutableListOf()
        currentList.addAll(list)
        searchesList.value = currentList
    }

    fun clearSearches(){
        searchesList.value = null
    }
}