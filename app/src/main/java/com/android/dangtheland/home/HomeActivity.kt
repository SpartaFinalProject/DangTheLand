package com.android.dangtheland.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.dangtheland.databinding.ActivityHomeBinding
import com.android.dangtheland.search.SearchActivity
import com.android.dangtheland.shelter.ShelterActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shelterButton.setOnClickListener{
            val shelterIntent = Intent(this, ShelterActivity::class.java)
            startActivity(shelterIntent)
        }

        binding.searchButton.setOnClickListener {
            val searchIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchIntent)
        }
    }
}