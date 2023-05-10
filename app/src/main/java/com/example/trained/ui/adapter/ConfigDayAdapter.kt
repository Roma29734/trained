package com.example.trained.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.ConfigAdapterModel
import com.example.trained.R
import com.example.trained.databinding.CardConfigDayBinding
import com.example.trained.utils.Utils.getDecryptedWeek

class ConfigDayAdapter : RecyclerView.Adapter<ConfigDayAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: CardConfigDayBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var configDayList = emptyList<ConfigAdapterModel>()

    var clickInFirstButton : ((day: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardConfigDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionDay = configDayList[position]
        Log.d("configFragment", "$positionDay")
        holder.binding.apply {
            if (!positionDay.openState) {
                cardViewFocusable.visibility = View.GONE
                cardViewNonFocusable.visibility = View.VISIBLE

                val day = getDecryptedWeek(positionDay.day)
                textViewDateNonFocusable.text = day
                imageButtonNonFocusable.setOnClickListener {
                    configDayList[position].openState = !configDayList[position].openState
                    notifyDataSetChanged()
                }
            } else {
                cardViewFocusable.visibility = View.VISIBLE
                cardViewNonFocusable.visibility = View.GONE
                if(positionDay.workout.isNotEmpty()) {
                    imageButtonFocusable.setImageResource(R.drawable.ic_edit)
                } else {
                    imageButtonFocusable.setImageResource(R.drawable.ic_add)
                }
                val day = getDecryptedWeek(positionDay.day)
                textDateFocusable.text = day
                val adapter = WorkoutConfigAdapter()
                recyclerViewFocusable.adapter = adapter
                recyclerViewFocusable.isNestedScrollingEnabled = false
                recyclerViewFocusable.layoutManager = GridLayoutManager(holder.itemView.context, 2)
                adapter.setWorkout(positionDay.workout)
                imageButtonFocusable.setOnClickListener {
                    clickInFirstButton?.let { it1 -> it1(positionDay.day) }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        val result = configDayList.size
        Log.d("configFragment", "$result")
        return result
    }

    fun setConfigDay(list: List<ConfigAdapterModel>) {
        configDayList = list
        notifyDataSetChanged()
    }
}