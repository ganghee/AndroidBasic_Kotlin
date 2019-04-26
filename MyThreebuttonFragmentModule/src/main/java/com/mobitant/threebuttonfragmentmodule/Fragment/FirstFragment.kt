package com.mobitant.threebuttonfragmentmodule.Fragment


import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class FirstFragment : ListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datas = arrayOf("박찬호", "류현진", "김현수", "오승환")
        val aa = ArrayAdapter(
            activity!!,
            android.R.layout.simple_list_item_1, datas
        )
        listAdapter = aa
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val toast = Toast.makeText(activity, l!!.adapter.getItem(position) as String, Toast.LENGTH_LONG)
        toast.show()
    }
}