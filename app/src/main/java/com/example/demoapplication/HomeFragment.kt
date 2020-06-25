package com.example.demoapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.demoapplication.data.models.Card
import com.example.demoapplication.ui.adapters.CardListAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.abs

class HomeFragment : Fragment() {

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
    }

    private fun loadIndicators() {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        Card.getListOfCards().forEach {
             val view = ImageView(context)
             view.setImageResource(R.drawable.tab_unselected_dot_new)
            view.layoutParams = layoutParams
             viewPagerIndicatorLayout.addView(view)
        }
    }

    private fun setCurrentPageIndicator(position:Int){
        for (i in 0 until viewPagerIndicatorLayout.childCount){
            val imageView = viewPagerIndicatorLayout.getChildAt(i) as ImageView
            if(position==i){
                imageView.setImageResource(R.drawable.tab_selected_dot_new)
            }else{
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
        cardListViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentPageIndicator(position)
            }
        })
        var pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(36))
        pageTransformer.addTransformer { page, position ->
            Log.d("TAG", "PAGE: "+page)
            Log.d("TAG", "POSITION: "+position)
            val f: Float = 1 - abs(position)
            Log.d("TAG", "F: "+f)
            Log.d("TAG", "scaleValue: "+(0.85f + (f*0.15f)))
            page.scaleY = 0.85f + (f*0.15f)

        }
        cardListViewPager.setPageTransformer(pageTransformer)

        TabLayoutMediator(tabLayout, cardListViewPager) { tab, position ->
            //Some implementation
        }.attach()
    }


}