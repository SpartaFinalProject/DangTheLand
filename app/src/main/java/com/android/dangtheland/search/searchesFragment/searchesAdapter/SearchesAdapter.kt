package com.android.dangtheland.search.searchesFragment.searchesAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.dangtheland.databinding.ItemRecyclerViewSearchBinding
import com.android.dangtheland.search.searchesFragment.searchesItemModel.SearchesData
import com.bumptech.glide.Glide


class SearchesAdapter(private var searchesList: MutableList<SearchesData>) : RecyclerView.Adapter<SearchesAdapter.Holder>(){

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchesAdapter.Holder {
        val binding = ItemRecyclerViewSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: SearchesAdapter.Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        Glide.with(holder.itemView.context)
            .load(searchesList[position].filename)
            .into(holder.image)
        holder.dogKind.text = searchesList[position].kindCd
        var text1 = "#${searchesList[position].age}"
        text1 += "#${searchesList[position].careAddr}"
        text1 += "#${searchesList[position].processState}"
        holder.age.text = text1
        var text2 = "#${searchesList[position].sexCd}"
        text2 += "#${searchesList[position].neuterYn}"
        text2 += "#${searchesList[position].weight}"
        holder.sexCd.text = text2
        holder.details.text = searchesList[position].specialMark
        Log.d("recyclerView", searchesList.size.toString())

    }

    override fun getItemCount(): Int {
        return searchesList.size
    }

    inner class Holder(binding: ItemRecyclerViewSearchBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.titleImage
        val dogKind = binding.dogKind
        val age = binding.age
        val sexCd = binding.sexCd
        val details = binding.details
    }

    fun searchesData(list : List<SearchesData>){
        searchesList = list.toMutableList()
        notifyDataSetChanged()
    }
}