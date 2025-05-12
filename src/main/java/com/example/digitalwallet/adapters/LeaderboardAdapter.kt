package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.R
import com.example.digitalwallet.databinding.ItemLeaderboardBinding
import com.example.digitalwallet.models.LeaderboardUser

class LeaderboardAdapter(private val users: List<LeaderboardUser>) : 
    RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = ItemLeaderboardBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return LeaderboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(users[position], position)
    }

    override fun getItemCount() = users.size

    class LeaderboardViewHolder(private val binding: ItemLeaderboardBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(user: LeaderboardUser, position: Int) {
            // Set rank
            if (position == 0) {
                binding.textRank.visibility = ViewGroup.GONE
                binding.imageTrophy.visibility = ViewGroup.VISIBLE
            } else {
                binding.textRank.visibility = ViewGroup.VISIBLE
                binding.imageTrophy.visibility = ViewGroup.GONE
                binding.textRank.text = (position + 1).toString()
            }
            
            // Set user name
            binding.textUserName.text = user.name
            
            // Set points
            binding.textUserPoints.text = "${user.points} pts"
            
            // Highlight current user
            if (user.isCurrentUser) {
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.blue_violet_10))
                binding.textUserName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.blue_violet))
            } else {
                binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.transparent))
                binding.textUserName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.text_primary))
            }
            
            // Set avatar (in a real app, you would use a library like Glide or Picasso)
            // For now, we'll just use a placeholder
            binding.imageAvatar.setImageResource(R.drawable.ic_user)
        }
    }
}

