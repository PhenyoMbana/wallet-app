package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digitalwallet.databinding.FragmentReportsBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupTabLayout()
        setupCategoryChart()
        setupMonthlyChart()
        setupPieChart()
    }
    
    private fun setupTabLayout() {
        // Set up tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Categories"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Monthly"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Distribution"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.cardCategoryChart.visibility = View.VISIBLE
                        binding.cardMonthlyChart.visibility = View.GONE
                        binding.cardPieChart.visibility = View.GONE
                    }
                    1 -> {
                        binding.cardCategoryChart.visibility = View.GONE
                        binding.cardMonthlyChart.visibility = View.VISIBLE
                        binding.cardPieChart.visibility = View.GONE
                    }
                    2 -> {
                        binding.cardCategoryChart.visibility = View.GONE
                        binding.cardMonthlyChart.visibility = View.GONE
                        binding.cardPieChart.visibility = View.VISIBLE
                    }
                }
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
    
    private fun setupCategoryChart() {
        // Mock data for category chart
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 350f))
        entries.add(BarEntry(1f, 500f))
        entries.add(BarEntry(2f, 120f))
        entries.add(BarEntry(3f, 80f))
        entries.add(BarEntry(4f, 100f))
        entries.add(BarEntry(5f, 100f))
        
        val labels = arrayOf("Groceries", "Rent", "Utilities", "Entertainment", "Transport", "Shopping")
        
        val dataSet = BarDataSet(entries, "Expenses")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        
        val data = BarData(dataSet)
        data.barWidth = 0.6f
        
        binding.chartCategory.data = data
        binding.chartCategory.description.isEnabled = false
        binding.chartCategory.legend.isEnabled = false
        
        val xAxis = binding.chartCategory.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        
        binding.chartCategory.axisLeft.axisMinimum = 0f
        binding.chartCategory.axisRight.isEnabled = false
        
        binding.chartCategory.invalidate()
    }
    
    private fun setupMonthlyChart() {
        // Mock data for monthly chart
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 1200f))
        entries.add(BarEntry(1f, 1100f))
        entries.add(BarEntry(2f, 1300f))
        entries.add(BarEntry(3f, 900f))
        entries.add(BarEntry(4f, 1500f))
        entries.add(BarEntry(5f, 1250f))
        
        val labels = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        
        val dataSet = BarDataSet(entries, "Monthly Expenses")
        dataSet.color = resources.getColor(com.example.digitalwallet.R.color.blue_violet, null)
        
        val data = BarData(dataSet)
        data.barWidth = 0.6f
        
        binding.chartMonthly.data = data
        binding.chartMonthly.description.isEnabled = false
        binding.chartMonthly.legend.isEnabled = false
        
        val xAxis = binding.chartMonthly.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(false)
        
        binding.chartMonthly.axisLeft.axisMinimum = 0f
        binding.chartMonthly.axisRight.isEnabled = false
        
        binding.chartMonthly.invalidate()
    }
    
    private fun setupPieChart() {
        // Mock data for pie chart
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(350f, "Groceries"))
        entries.add(PieEntry(500f, "Rent"))
        entries.add(PieEntry(120f, "Utilities"))
        entries.add(PieEntry(80f, "Entertainment"))
        entries.add(PieEntry(100f, "Transport"))
        entries.add(PieEntry(100f, "Shopping"))
        
        val dataSet = PieDataSet(entries, "Expenses")
        dataSet.colors = listOf(
            resources.getColor(com.example.digitalwallet.R.color.blue_violet, null),
            resources.getColor(com.example.digitalwallet.R.color.exuberant_orange, null),
            resources.getColor(com.example.digitalwallet.R.color.sun_glare, null),
            resources.getColor(com.example.digitalwallet.R.color.primary_variant, null),
            resources.getColor(com.example.digitalwallet.R.color.secondary_variant, null),
            resources.getColor(com.example.digitalwallet.R.color.error, null)
        )
        
        val data = PieData(dataSet)
        
        binding.chartPie.data = data
        binding.chartPie.description.isEnabled = false
        binding.chartPie.setUsePercentValues(true)
        binding.chartPie.setDrawEntryLabels(false)
        binding.chartPie.legend.isEnabled = true
        binding.chartPie.centerText = "Expenses"
        binding.chartPie.setCenterTextSize(14f)
        
        binding.chartPie.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

