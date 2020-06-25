package com.example.demoapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.data.models.Card
import com.example.demoapplication.databinding.ListItemLayoutBinding

class TestListAdapter(private val listOfTitles:List<String>): RecyclerView.Adapter<TestListAdapter.TestListViewHolder>() {

    private lateinit var binding:ListItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestListViewHolder {
        binding =  ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false);
        return TestListViewHolder(binding);
    }

    override fun getItemCount(): Int = listOfTitles.size

    override fun onBindViewHolder(holder: TestListViewHolder, position: Int) {
        holder.bindView(listOfTitles[position])
    }


    inner class TestListViewHolder(binding:ListItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(title: String) {
            binding.tvTitle.text = title
        }

    }

}