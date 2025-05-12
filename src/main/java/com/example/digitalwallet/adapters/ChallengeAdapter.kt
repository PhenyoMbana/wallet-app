package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemChallengeBinding
import com.example.digitalwallet.models.Challenge

class ChallengeAdapter(private val challenges: List<Challenge>) : 
    RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemChallengeBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(challenges[position])
    }

    override fun getItemCount() = challenges.size

    class ChallengeViewHolder(private val binding: ItemChallengeBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(challenge: Challenge) {
            binding.textChallengeTitle.text = challenge.title
            binding.textChallengeDescription.text = challenge.description
            binding.imageChallengeIcon.setImageResource(challenge.iconResId)
            binding.textChallengeReward.text = "${challenge.reward} pts"
            binding.progressChallenge.progress = challenge.progress
            binding.textChallengeProgress.text = "${challenge.progress}%"
            binding.textChallengeDaysLeft.text = "${challenge.daysLeft} days left"
        }
    }
}

