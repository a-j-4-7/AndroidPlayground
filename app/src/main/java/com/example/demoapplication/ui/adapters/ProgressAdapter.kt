package com.example.demoapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.data.models.Card
import com.example.demoapplication.databinding.ListItemLayoutBinding
import com.example.demoapplication.databinding.ProgressItemLayoutBinding

class ProgressAdapter(): RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    private lateinit var binding:ProgressItemLayoutBinding
    private var isVisible = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        binding =  ProgressItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false);
        return ProgressViewHolder(binding);
    }

    override fun getItemCount(): Int{
        return if(isVisible){
            1
        }else {
            0
        }
    }

    fun changeProgressVisibility(isVisible:Boolean){
        this.isVisible = isVisible
        if(isVisible){
            notifyItemInserted(0)
        }else{
            notifyItemRemoved(0)
        }
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
    }


    inner class ProgressViewHolder(binding:ProgressItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

}