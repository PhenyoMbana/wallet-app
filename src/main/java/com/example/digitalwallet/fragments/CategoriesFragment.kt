package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.adapters.CategoryAdapter
import com.example.digitalwallet.databinding.FragmentCategoriesBinding
import com.example.digitalwallet.models.Category
import com.google.android.material.tabs.TabLayoutMediator

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupCategoryList()
        setupTabLayout()
    }
    
    private fun setupCategoryList() {
        // Mock data for categories
        val categories = listOf(
            Category(1, "Groceries", 400.0, "#4CAF50"),
            Category(2, "Housing", 1500.0, "#2196F3"),
            Category(3, "Transportation", 200.0, "#FFC107"),
            Category(4, "Entertainment", 150.0, "#9C27B0"),
            Category(5, "Food", 300.0, "#F44336"),
            Category(6, "Utilities", 250.0, "#3F51B5"),
            Category(7, "Shopping", 200.0, "#E91E63")
        )
        
        categoryAdapter = CategoryAdapter(categories)
        binding.recyclerCategories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }
    
    private fun setupTabLayout() {
        // Set up tabs for list and add new
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All Categories"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Add New"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.recyclerCategories.visibility = View.VISIBLE
                        binding.layoutAddCategory.visibility = View.GONE
                    }
                    1 -> {
                        binding.recyclerCategories.visibility = View.GONE
                        binding.layoutAddCategory.visibility = View.VISIBLE
                    }
                }
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

