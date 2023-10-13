package com.android.dangtheland.shelter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.dangtheland.R
import com.android.dangtheland.databinding.ActivityShelterBinding
import com.android.dangtheland.home.HomeActivity
import com.android.dangtheland.search.SearchActivity

class ShelterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShelterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShelterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeButton.setOnClickListener{
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        }
    }
}