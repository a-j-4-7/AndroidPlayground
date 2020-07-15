package com.example.demoapplication.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.ui.adapters.ProgressAdapter
import com.example.demoapplication.ui.adapters.TestListAdapter
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment(R.layout.fragment_stats) {

    private val list = mutableListOf<String>()
    private var startPoint = 0
    private lateinit var listAdapter: TestListAdapter
    private lateinit var progressAdapter: ProgressAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.generateList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        progressAdapter = ProgressAdapter()
        listAdapter = TestListAdapter(list)
        val mergeAdapter = MergeAdapter(listAdapter, progressAdapter)
        recyclerView.adapter = mergeAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(dy>0){
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if(layoutManager.findLastCompletelyVisibleItemPosition() == list.size-1){
                        Log.d("TAG", "onScrolled: ")
                        progressAdapter.changeProgressVisibility(true)
                        val runnable = Runnable {
                            addMoreData()
                        }
                        Handler().postDelayed(runnable, 3500)
                    }
                }
            }
        })
    }

    private fun generateList(){
        (0..20).forEach {
            startPoint = it
            list.add("Title ${it + 1}")
        }
        Log.d("TAG", "generateList: "+list)
    }

    private fun addMoreData(){
        (startPoint..startPoint+20).forEach {
            startPoint = it
            list.add("Title ${it + 1}")
        }
        Log.d("TAG", "generateList: "+list)
        progressAdapter.changeProgressVisibility(false)
    }


}