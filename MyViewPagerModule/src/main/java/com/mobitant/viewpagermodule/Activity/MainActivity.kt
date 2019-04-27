package com.mobitant.viewpagermodule.Activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.mobitant.viewpagermodule.Adapter.PagerAdapter
import com.mobitant.viewpagermodule.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureMainTab()
    }

    //TabLayout의 OnTabSelectedListener를 상속받아 구현할 때
    //아래 3개의 메서드가 오버라이드된다.
    //3개의 메서드 중에 onTabSelected만 구현한다.
    override fun onTabReselected(p0: TabLayout.Tab?) {
       }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
       }

    //탭이 선택 되었을 때 나타나게 될 Pager를 구현한다.
    override fun onTabSelected(p0: TabLayout.Tab?) {
        viewPager.currentItem = p0!!.position
    }

    //viewPager는 adapter를 반드시 이용해야 한다.
    //tabLayout에 adapter설정을 한 viewPager를 붙인다.
    //navCategoryMainLayout은 뷰를 의미하며 이 뷰는 탭디자인의 xml을 가져온다.
    //tabLayout에 탭xml의 id를 가져와 탭의 정보를 연동한다.
    private fun configureMainTab() {
        viewPager.adapter =  PagerAdapter(supportFragmentManager,3)
        viewPager.offscreenPageLimit =2
        tabLayout.setupWithViewPager(viewPager)

        val navCategoryMainLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.navigation_category, null, false)

        tabLayout.getTabAt(0)!!.customView = navCategoryMainLayout.findViewById(R.id.navigation_category_first) as RelativeLayout
        tabLayout.getTabAt(1)!!.customView = navCategoryMainLayout.findViewById(R.id.navigation_category_second) as RelativeLayout
        tabLayout.getTabAt(2)!!.customView = navCategoryMainLayout.findViewById(R.id.navigation_category_third) as RelativeLayout
    }

}
