package com.example.digitalwallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.digitalwallet.databinding.ActivityMainBinding
import com.example.digitalwallet.fragments.BudgetFragment
import com.example.digitalwallet.fragments.DashboardFragment
import com.example.digitalwallet.fragments.ExpensesFragment
import com.example.digitalwallet.fragments.MoreFragment
import com.example.digitalwallet.fragments.RewardsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        replaceFragment(DashboardFragment())

        // Set up bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(DashboardFragment())
                R.id.nav_expenses -> replaceFragment(ExpensesFragment())
                R.id.nav_budget -> replaceFragment(BudgetFragment())
                R.id.nav_rewards -> replaceFragment(RewardsFragment())
                R.id.nav_more -> replaceFragment(MoreFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

