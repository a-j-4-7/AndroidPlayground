package com.example.demoapplication.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.demoapplication.R
import com.example.demoapplication.data.models.Card
import com.example.demoapplication.ui.adapters.CardListAdapter
import com.example.demoapplication.ui.viewModels.HomeFragmentViewModel
import com.example.demoapplication.util.Output
import com.example.demoapplication.util.SharedPrefsUtils
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeVM: HomeFragmentViewModel by viewModels()

    @Inject
    lateinit var sharedPrefsUtils: SharedPrefsUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagerAdapter()
        loadIndicators()
        setCurrentPageIndicator(0)
        observeChanges()
        Log.d("TAG", "onViewCreated: ")
        Log.d("TAG", "SAVED DATA: "+sharedPrefsUtils.getString("KEY"))
        homeVM.handleEvent(HomeFragmentViewModel.HomeEvent.fetchNotes)
    }

    private fun observeChanges() {
        homeVM._notesLiveDataAlt.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "NOTES: "+it)
        })

        homeVM._notesLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Output.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Output.Success -> {
                    progressBar.visibility = View.GONE
                    var sb = StringBuffer()
                    for (note in result.data){
                        sb.append(note.title + "\n")
                    }
                    apiResultTextView.text = sb.toString()
                    this.saveToSharedPrefs(sb)

                }

                is Output.Error -> {
                    progressBar.visibility = View.GONE
                    apiResultTextView.text = result.exception.message
                }
            }
        })
    }

    private fun saveToSharedPrefs(sb: StringBuffer) {
       sharedPrefsUtils.saveToSharedPrefs(sb.toString())
    }

    private fun loadIndicators() {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        Card.getListOfCards().forEach {
            val view = ImageView(context)
            view.setImageResource(R.drawable.tab_unselected_dot_new)
            view.layoutParams = layoutParams
            viewPagerIndicatorLayout.addView(view)
        }
    }

    private fun setCurrentPageIndicator(position: Int) {
        for (i in 0 until viewPagerIndicatorLayout.childCount) {
            val imageView = viewPagerIndicatorLayout.getChildAt(i) as ImageView
            if (position == i) {
                imageView.setImageResource(R.drawable.tab_selected_dot_new)
            } else {
                imageView.setImageResource(R.drawable.tab_unselected_dot_new)
            }
        }

    }

    private fun initViewPagerAdapter() {
        val adapter = CardListAdapter(Card.getListOfCards())
        cardListViewPager.adapter = adapter
        cardListViewPager.clipToPadding = false;
        cardListViewPager.clipChildren = false;
        cardListViewPager.offscreenPageLimit = 3
        cardListViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentPageIndicator(position)
            }
        })
        var pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(36))
        pageTransformer.addTransformer { page, position ->
            Log.d("TAG", "PAGE: " + page)
            Log.d("TAG", "POSITION: " + position)
            val f: Float = 1 - abs(position)
            Log.d("TAG", "F: " + f)
            Log.d("TAG", "scaleValue: " + (0.85f + (f * 0.15f)))
            page.scaleY = 0.85f + (f * 0.15f)

        }
        cardListViewPager.setPageTransformer(pageTransformer)

        TabLayoutMediator(tabLayout, cardListViewPager) { tab, position ->
            //Some implementation
        }.attach()
    }


}