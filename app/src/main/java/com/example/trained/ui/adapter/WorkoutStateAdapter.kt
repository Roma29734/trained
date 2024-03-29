package com.example.trained.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.transit.TransitDailyWorkoutModel
import com.example.domain.model.DailyStatisticsModel
import com.example.domain.model.DailyWorkoutDomainModel
import com.example.trained.databinding.CardTreinyHomeBinding


class WorkoutStateAdapter() : RecyclerView.Adapter<WorkoutStateAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CardTreinyHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var dayWorkout = emptyList<DailyWorkoutDomainModel>()

    var callBackDel: ((model: TransitDailyWorkoutModel) -> Unit)? = null


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

        if (positionWorkout.sumApproach == positionWorkout.completedApproach) {
            val myColor = ContextCompat.getColor(
                holder.itemView.context,
                com.example.trained.R.color.secondAccent
            )
            holder.binding.imgSuccess.visibility = View.VISIBLE
            holder.binding.textQuantity.visibility = View.GONE
            holder.binding.cardView.setCardBackgroundColor(myColor)
        } else {
            holder.binding.textQuantity.text = positionWorkout.receptions.toString()
        }
        if (positionWorkout.completedApproach > 0) {
            holder.binding.textApproach.text =
                "${positionWorkout.completedApproach}/${positionWorkout.sumApproach}"
        } else {
            holder.binding.textApproach.text = positionWorkout.sumApproach.toString()
        }
        holder.binding.textNameWorkout.text = positionWorkout.nameWorkout
        Log.d("WorkoutStateAdapter", positionWorkout.sumApproach.toString())


        holder.binding.cardView.setOnClickListener {
            val model = TransitDailyWorkoutModel(
                id = position,
                nameWorkout = positionWorkout.nameWorkout,
                sumApproach = positionWorkout.sumApproach,
                completedApproach = positionWorkout.completedApproach,
                receptions = positionWorkout.receptions,
                projectileWeight = positionWorkout.projectileWeight
            )
            callBackDel?.let { it1 -> it1(model) }
        }
    }

    override fun getItemCount(): Int {
        return dayWorkout.size
    }

    fun setWorkout(list: List<DailyWorkoutDomainModel>) {
        dayWorkout = list
        notifyDataSetChanged()
    }
}