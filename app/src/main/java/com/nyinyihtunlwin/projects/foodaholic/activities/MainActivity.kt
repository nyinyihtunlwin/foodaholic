package com.nyinyihtunlwin.projects.foodaholic.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.adapters.SectionPagerAdapter
import com.nyinyihtunlwin.projects.foodaholic.databinding.ActivityMainBinding
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var contentView: ActivityMainBinding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )
        setSupportActionBar(toolbar)

        //   tv_app_name.typeface = Typeface.createFromAsset(assets, "entsans.ttf")

        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        vp_meal.adapter = sectionPagerAdapter

        vp_meal.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setSectionTitle(position)
            }
        })
        tabs.addOnTabSelectedListener(object : TabLayout.ViewPagerOnTabSelectedListener(vp_meal) {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_meal.currentItem = tab.position
                setSectionTitle(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })


    }

    private fun setSectionTitle(position: Int) {
        var sectionTitle = ""
        when (position) {
            0 -> sectionTitle = "Latest"
            1 -> sectionTitle = "Categories"
        }
        tv_section_name.text = sectionTitle
    }


}
