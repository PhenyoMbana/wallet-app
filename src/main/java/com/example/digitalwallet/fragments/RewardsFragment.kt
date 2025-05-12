package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.R
import com.example.digitalwallet.adapters.BadgeAdapter
import com.example.digitalwallet.adapters.ChallengeAdapter
import com.example.digitalwallet.adapters.LeaderboardAdapter
import com.example.digitalwallet.databinding.FragmentRewardsBinding
import com.example.digitalwallet.models.Badge
import com.example.digitalwallet.models.Challenge
import com.example.digitalwallet.models.LeaderboardUser

class RewardsFragment : Fragment() {

    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var challengeAdapter: ChallengeAdapter
    private lateinit var badgeAdapter: BadgeAdapter
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUserPoints()
        setupTabLayout()
        setupChallenges()
        setupBadges()
        setupLeaderboard()
    }
    
    private fun setupUserPoints() {
        // Set user points
        binding.textUserPoints.text = "450 pts"
    }
    
    private fun setupTabLayout() {
        // Set up tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Challenges"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Badges"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Leaderboard"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.recyclerChallenges.visibility = View.VISIBLE
                        binding.recyclerBadges.visibility = View.GONE
                        binding.cardLeaderboard.visibility = View.GONE
                    }
                    1 -> {
                        binding.recyclerChallenges.visibility = View.GONE
                        binding.recyclerBadges.visibility = View.VISIBLE
                        binding.cardLeaderboard.visibility = View.GONE
                    }
                    2 -> {
                        binding.recyclerChallenges.visibility = View.GONE
                        binding.recyclerBadges.visibility = View.GONE
                        binding.cardLeaderboard.visibility = View.VISIBLE
                    }
                }
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
    
    private fun setupChallenges() {
        // Mock data for challenges
        val challenges = listOf(
            Challenge(
                "No-Spend Weekend",
                "Don't spend any money this weekend",
                R.drawable.ic_target,
                50,
                25,
                5
            ),
            Challenge(
                "Save R100",
                "Save R100 this month",
                R.drawable.ic_gift,
                100,
                65,
                12
            ),
            Challenge(
                "Budget Master",
                "Stay under budget for 3 categories",
                R.drawable.ic_award,
                75,
                33,
                18
            )
        )
        
        // Set up adapter
        challengeAdapter = ChallengeAdapter(challenges)
        binding.recyclerChallenges.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = challengeAdapter
        }
    }
    
    private fun setupBadges() {
        // Mock data for badges
        val badges = listOf(
            Badge(
                "Budget Pro",
                "Set up your first budget",
                R.drawable.ic_pie_chart,
                true,
                "Mar 15, 2024"
            ),
            Badge(
                "Saver",
                "Save R500 total",
                R.drawable.ic_award,
                true,
                "Mar 10, 2024"
            ),
            Badge(
                "Tracker",
                "Log expenses for 30 days",
                R.drawable.ic_credit_card,
                false,
                progress = 80
            ),
            Badge(
                "Debt Crusher",
                "Pay off a debt",
                R.drawable.ic_trophy,
                false,
                progress = 25
            )
        )
        
        // Set up adapter
        badgeAdapter = BadgeAdapter(badges)
        binding.recyclerBadges.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = badgeAdapter
        }
    }
    
    private fun setupLeaderboard() {
        // Mock data for leaderboard
        val users = listOf(
            LeaderboardUser(1, "Sarah J.", 780, "/placeholder.svg?height=40&width=40", false),
            LeaderboardUser(2, "You", 450, "/placeholder.svg?height=40&width=40", true),
            LeaderboardUser(3, "Mike T.", 420, "/placeholder.svg?height=40&width=40", false),
            LeaderboardUser(4, "Emma R.", 380, "/placeholder.svg?height=40&width=40", false),
            LeaderboardUser(5, "David K.", 310, "/placeholder.svg?height=40&width=40", false)
        )
        
        // Set up adapter
        leaderboardAdapter = LeaderboardAdapter(users)
        binding.recyclerLeaderboard.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = leaderboardAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

