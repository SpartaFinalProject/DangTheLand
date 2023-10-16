package com.android.dangtheland.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.dangtheland.R
import com.android.dangtheland.databinding.ActivitySearchBinding
import com.android.dangtheland.home.HomeActivity
import com.android.dangtheland.retrofit.Constants
import com.android.dangtheland.retrofit.DangClient.api
import com.android.dangtheland.retrofit.abandonedDog.AbandonedDog
import com.android.dangtheland.search.recentFragment.RecentWordFragment
import com.android.dangtheland.search.searchesFragment.SearchesFragment
import com.android.dangtheland.search.searchesFragment.searchesItemModel.SearchesData
import com.android.dangtheland.search.searchesFragment.searchesViewModel.SearchesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var searchesViewModel : SearchesViewModel

    private var searchesItem = mutableListOf<SearchesData>()

    private val searchesFragment = SearchesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener{
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        }

        setFragment(RecentWordFragment())

        binding.apply {
            searchEdit.setOnClickListener {
                setFragment(RecentWordFragment())
            }
            searchImage.setOnClickListener {
                setFragment(SearchesFragment())
                searchesData()
            }
        }

        searchesViewModel = ViewModelProvider(this)[SearchesViewModel::class.java]

        searchesViewModel.searchesList.observe(this, Observer { list ->
            searchesFragment.searchesData(list as MutableList<SearchesData>)
        })

    }
    private fun setFragment(frag : Fragment) {
        supportFragmentManager.commit {
            replace(R.id.searchFragment, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    private fun searchesData(){
        api.abandonedDogSearch(
            Constants.AUTH_HEADER,
            417000,
            "json",
            10,
            "000054"
        ).enqueue(object  : Callback<AbandonedDog?>{
            override fun onResponse(call: Call<AbandonedDog?>, response: Response<AbandonedDog?>) {
                if (response.isSuccessful){
                    val abandonedDog = response.body()
                    abandonedDog?.response?.body?.items?.item?.forEach { item ->
                        val image = item.filename
                        val kindCd = item.kindCd
                        val age = item.age
                        val careAddr = item.careAddr
                        val processState = item.processState
                        val sexCd = item.sexCd
                        val neuterYn = item.neuterYn
                        val weight = item.weight
                        val specialMark = item.specialMark

                        searchesItem.add(SearchesData(image, kindCd, age, careAddr, processState, sexCd, neuterYn, weight, specialMark))
                    }
                } else {
                    Log.e("api", "Error: ${response.errorBody()}")
                }
                searchesViewModel.searches(searchesItem)
            }

            override fun onFailure(call: Call<AbandonedDog?>, t: Throwable) {
                Log.e("#api1", "실패: ${t.message}")
            }

        })
    }
}