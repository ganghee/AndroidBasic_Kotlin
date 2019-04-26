package com.mobitant.threebuttonfragmentmodule.Activity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mobitant.threebuttonfragmentmodule.Fragment.FirstFragment
import com.mobitant.threebuttonfragmentmodule.Fragment.SecondFragment
import com.mobitant.threebuttonfragmentmodule.Fragment.ThirdFragment
import com.mobitant.threebuttonfragmentmodule.R
import kotlinx.android.synthetic.main.activity_threebutton.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ThreebuttonActivity : AppCompatActivity() {

    var first = FirstFragment()
    var second = SecondFragment()
    var third = ThirdFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threebutton)

        //프래그먼트를 첫 번째로 보여줍니다.
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,FirstFragment())
            .commit()

        main_btn1.onClick {
            if (!first.isVisible) {
                supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, first)
                .commit()
            }
        }
        main_btn2.onClick {
            if (!second.isVisible) {
                second.show(supportFragmentManager, null)
            }
        }

        main_btn3.onClick {
            if (!third.isVisible) {
                supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, third)
                .commit()
            }
        }
    }
}