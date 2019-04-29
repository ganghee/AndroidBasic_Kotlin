package com.mobitant.myrecyclerviewmodule.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.mobitant.myrecyclerviewmodule.Adapter.ItemRecyclerViewAdapter
import com.mobitant.myrecyclerviewmodule.R
import com.mobitant.myrecyclerviewmodule.VO.CharacterItem
import kotlinx.android.synthetic.main.activity_recycler.*
import android.content.Context as ContentContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        var dataList: ArrayList<CharacterItem> = ArrayList()
        dataList.add(
            CharacterItem(
                "http://cfile225.uf.daum.net/image/994B85465BAB24493A4272"
                ,0,"라이언", 120,"부끄")
        )
        dataList.add(
            CharacterItem(
                "http://cfile202.uf.daum.net/image/999575465BAB244A359D53"
                ,1,"라이언", 100,"안녕")
        )
        dataList.add(
            CharacterItem(
                "http://cfile218.uf.daum.net/image/99B568465BAB244B330260"
                ,2,"라이언", 99,"크흠")
        )
        dataList.add(
            CharacterItem(
                "http://cfile217.uf.daum.net/image/99291E485BAB2454362CF3"
                ,3,"라이언", 10,"필요없어")
        )
        dataList.add(
            CharacterItem(
                "http://cfile210.uf.daum.net/image/993F66495BAB249430D540"
                ,4,"라이언", 1,"잘했어")
        )
        dataList.add(
            CharacterItem(
                "http://cfile232.uf.daum.net/image/995EF0505BAB25DC319863"
                ,5,"튜브 프로도 네오", 1,"으싸으싸")
        )
        dataList.add(
            CharacterItem(
                "http://cfile223.uf.daum.net/image/991E68495BAB24961524B8"
                ,6,"라이언 어파치 튜브", 1,"만세" )
        )
        dataList.add(
            CharacterItem(
                "http://cfile202.uf.daum.net/image/993AD1495BAB2497058F2E"
                ,7,"라이언 무지 어파치", 1,"돌리고")
        )

        //activity는 this fragment는 context
        recyclerview.adapter = ItemRecyclerViewAdapter(this, dataList)
        recyclerview.layoutManager = GridLayoutManager(this, 3)
    }
}