package com.mobitant.mysqlitecrudmodule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    //dbHandler 객체를 한 번만 만들어서 재활용한다.
    companion object{
        lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dbHandler 객체를 초기화한다.
        dbHandler = DBHandler(this,null,null,1)

        viewCustomers()

        fab.onClick {
            startActivity<AddCustomerActivity>()
        }
    }

    //db에 있는 customer데이터를 adapter에 연결한다.
    //recyclerView와 dapter를 연결하여 화면에 db에 저장된 데이터를 보여준다.
    private fun viewCustomers() {
        val customerslist = dbHandler.getCustomers(this)
        val adapter = CustomerAdapter(this, customerslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    //onResume는 액티비티가 전면에 보여지기 직전에 실행된다.
    //데이터들이 바뀔때마다 바로 화면에 보여줘야 하기 때문에 onResume()메소드에 구현한다.
    override fun onResume() {
        viewCustomers()
        super.onResume()
    }
}
