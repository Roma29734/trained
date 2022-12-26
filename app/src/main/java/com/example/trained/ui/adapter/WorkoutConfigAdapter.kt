package com.example.trained.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trained.data.model.WorkoutModel
import com.example.trained.databinding.CardTreinyHomeBinding

class WorkoutConfigAdapter: RecyclerView.Adapter<WorkoutConfigAdapter.MyViewHolder>() {

    private var workout = emptyList<WorkoutModel>()

    inner class MyViewHolder(val binding: CardTreinyHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardTreinyHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionWorkout = workout[position]

        holder.binding.textNameWorkout.text = positionWorkout.nameExercise
        holder.binding.textApproach.text = positionWorkout.approaches.toString()
        holder.binding.textQuantity.text = positionWorkout.repetitions.toString()
    }

    override fun getItemCount(): Int {
        return workout.size
    }

    fun setWorkout(list: List<WorkoutModel>) {
        workout = list
        notifyDataSetChanged()
    }
}