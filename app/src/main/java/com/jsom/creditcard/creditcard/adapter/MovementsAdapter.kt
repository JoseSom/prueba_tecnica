package com.jsom.creditcard.creditcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsom.creditcard.R
import com.jsom.creditcard.creditcard.data.model.Movement
import com.jsom.creditcard.databinding.ItemMovementBinding

class MovementsAdapter(
    private val movementsList: List<Movement>
) : RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovementBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movementsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movementsList[position]
        holder.binding.descriptionMovement.text = item.description
        holder.binding.dateMovement.text = item.date
        holder.binding.mountMovement.text = item.mount
    }

}