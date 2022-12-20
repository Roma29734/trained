package com.example.trained.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.WorkoutModel
import com.example.trained.databinding.CardTreinyHomeBinding
import com.example.trained.databinding.CardWorkoutHomeBinding

class WorkoutStateAdapter() : RecyclerView.Adapter<WorkoutStateAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CardWorkoutHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var dayWorkout = emptyList<WorkoutModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardWorkoutHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val positionWorkout = dayWorkout[position]

//        if(position == 1) {
//            val layoutParams = (holder.binding.cardView.layoutParams as? ViewGroup.MarginLayoutParams)
//            layoutParams?.setMargins(16,0,32,24)
//            holder.binding.cardView.layoutParams = layoutParams
//        }
        holder.binding.textNameExercise.text = positionWorkout.nameExercise
        holder.binding.textApproaches.text = positionWorkout.approaches.toString()
        holder.binding.textRepetitions.text = positionWorkout.repetitions.toString()
    }

    override fun getItemCount(): Int {
        return dayWorkout.size
    }

    fun setWorkout(list: List<WorkoutModel>) {
        dayWorkout = list
        notifyDataSetChanged()
    }
}