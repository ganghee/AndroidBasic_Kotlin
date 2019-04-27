package com.mobitant.viewpagermodule.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.mobitant.viewpagermodule.Fragment.FirstFragment
import com.mobitant.viewpagermodule.Fragment.SecondFragment
import com.mobitant.viewpagermodule.Fragment.ThirdFragment

//FragmentStatePagerAdapter은 하나의 액티비티에서 fragment가 변할 때
//깜박거리지 않고 이전의 상태를 그대로 유지시켜주는 역할을 한다.
//FragmentStatePagerAdapter를 상속받게 되면 getItem과 getCount를 오버라이딩할 수 있게 된다.

class PagerAdapter(fm: FragmentManager, private val num_fragment:Int): FragmentStatePagerAdapter(fm){

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> null
        }
        }

    override fun getCount(): Int {
        return num_fragment

    }

}