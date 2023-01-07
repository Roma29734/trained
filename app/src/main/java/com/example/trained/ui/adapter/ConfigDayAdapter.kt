package com.example.trained.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.ConfigAdapterModel
import com.example.trained.databinding.CardConfigDayBinding

class ConfigDayAdapter : RecyclerView.Adapter<ConfigDayAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: CardConfigDayBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var configDayList = emptyList<ConfigAdapterModel>()

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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return configDayList.size
    }

    fun setConfigDay(list: List<ConfigAdapterModel>) {
        configDayList = list
        notifyDataSetChanged()
    }
}