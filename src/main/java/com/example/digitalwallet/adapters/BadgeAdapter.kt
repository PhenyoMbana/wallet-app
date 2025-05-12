package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemBadgeBinding
import com.example.digitalwallet.models.Badge

class BadgeAdapter(private val badges: List<Badge>) : 
    RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val binding = ItemBadgeBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return BadgeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        holder.bind(badges[position])
    }

    override fun getItemCount() = badges.size

    class BadgeViewHolder(private val binding: ItemBadgeBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(badge: Badge) {
            binding.textBadgeTitle.text = badge.title
            binding.textBadgeDescription.text = badge.description
            binding.imageBadgeIcon.setImageResource(badge.iconResId)
            
            if (badge.earned) {
                binding.cardBadgeIcon.setCardBackgroundColor(binding.root.context.getColor(com.example.digitalwallet.R.color.sun_glare))
                binding.imageCheckmark.visibility = View.VISIBLE
                binding.textBadgeDate.visibility = View.VISIBLE
                binding.textBadgeDate.text = "Earned on ${badge.date}"
                
                binding.progressBadge.visibility = View.GONE
                binding.textBadgeProgress.visibility = View.GONE
            } else {
                binding.cardBadgeIcon.setCardBackgroundColor(binding.root.context.getColor(com.example.digitalwallet.R.color.muted))
                binding.imageCheckmark.visibility = View.GONE
                binding.textBadgeDate.visibility = View.GONE
                
                binding.progressBadge.visibility = View.VISIBLE
                binding.textBadgeProgress.visibility = View.VISIBLE
                binding.progressBadge.progress = badge.progress
                binding.textBadgeProgress.text = "${badge.progress}% complete"
            }
        }
    }
}

