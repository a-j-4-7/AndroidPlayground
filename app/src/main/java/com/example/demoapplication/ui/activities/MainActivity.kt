package com.example.demoapplication.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.demoapplication.R
import com.example.demoapplication.data.models.DropDownResponse
import com.example.demoapplication.network.RetrofitClient
import com.example.demoapplication.ui.base.BaseActivity
import com.example.demoapplication.ui.fragments.EditFragment
import com.example.demoapplication.ui.fragments.HomeFragment
import com.example.demoapplication.ui.fragments.StatsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private var VIEW_PAGER_INDEX = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        handleClicks()
//        networkCall();
    }

    private fun networkCall() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiClient = RetrofitClient()
            val response: Response<DropDownResponse> = apiClient.getDropdownValues()
            if(response.isSuccessful){
                val dropDownResponse = response.body()
                dropDownResponse?.data?.apply {
                    Log.d("RESPONSE", "country ${country!!}")
                    Log.d("RESPONSE", "state ${state!!}")
                    Log.d("RESPONSE", "district ${district!!}")
                    Log.d("RESPONSE", "city ${city!!}")

                }
            }
        }

    }


    private fun handleClicks() {
        ivBottomNavMenu1.setOnClickListener {
            viewPager.setCurrentItem(0,true)
        }
        ivBottomNavMenu2.setOnClickListener {
            viewPager.setCurrentItem(1,true)
        }
        ivBottomNavMenu3.setOnClickListener {
            viewPager.setCurrentItem(2,true)
        }
    }

    private fun initViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,0)
        viewPagerAdapter.addFragment(HomeFragment(),"Home")
        viewPagerAdapter.addFragment(StatsFragment(),"Stats")
        viewPagerAdapter.addFragment(EditFragment(),"Edit")
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = 3
        viewPager.currentItem = VIEW_PAGER_INDEX
        viewPager.addOnPageChangeListener(this)
    }

    inner class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {

        private var listOfFragments = mutableListOf<Fragment>()
        private var listOfTitle = mutableListOf<String>()

        override fun getItem(position: Int): Fragment = listOfFragments[position]

        override fun getCount(): Int = listOfFragments.size

        fun addFragment(fragment: Fragment, title:String){
            listOfFragments.add(fragment)
            listOfTitle.add(title)
        }

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
       this.changeBottomNavState(position)
    }

    private fun changeBottomNavState(position: Int) {
        ivBottomNavMenu1.setImageResource(
            when (position) {
                0 -> {
                    R.drawable.ic_home_selected
                }
                else -> {
                    R.drawable.ic_home_unselected
                }
            }
        )

        ivBottomNavMenu2.setImageResource(
            when (position) {
                1 -> {
                    R.drawable.ic_stats_selected
                }
                else -> {
                    R.drawable.ic_stats_unselected
                }
            }
        )

        ivBottomNavMenu3.setImageResource(
            when (position) {
                2 -> {
                    R.drawable.ic_edit_selected
                }
                else -> {
                    R.drawable.ic_edit_unselected
                }
            }
        )

    }

}