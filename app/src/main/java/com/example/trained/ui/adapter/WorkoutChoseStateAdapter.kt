package com.example.trained.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.DayWorkoutEntity
import com.example.data.model.WorkoutTransitionModel
import com.example.data.toTransit
import com.example.domain.model.DayWorkoutModel
import com.example.trained.databinding.CardTreinyHomeBinding

class WorkoutChoseStateAdapter: RecyclerView.Adapter<WorkoutChoseStateAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CardTreinyHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var dayWorkout = emptyList<DayWorkoutModel>()

    var callBackDel: ((model: WorkoutTransitionModel) -> Unit)? = null

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

        val positionWorkout = dayWorkout[position]

        holder.binding.textNameWorkout.text = positionWorkout.nameWorkout
        holder.binding.textApproach.text = positionWorkout.sumApproach.toString()
        holder.binding.textQuantity.text = positionWorkout.receptions.toString()

        holder.binding.cardView.setOnClickListener {
            val model = WorkoutTransitionModel(positionWorkout.toTransit(), 0)
            callBackDel?.let { it1 -> it1(model) }
        }
    }

    override fun getItemCount(): Int {
        return dayWorkout.size
    }

    fun setWorkout(list: List<DayWorkoutModel>) {
        dayWorkout = list
        notifyDataSetChanged()
    }
}