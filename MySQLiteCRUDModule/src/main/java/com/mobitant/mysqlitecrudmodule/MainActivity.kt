package com.mobitant.mysqlitecrudmodule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    //dbHandler 객체를 한 번만 만들어서 재활용한다.
    companion object{
        lateinit var dbHandler: DBHandler
    }

    var customerslist = ArrayList<Customer>()
    lateinit var adapter : RecyclerView.Adapter<*>
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //dbHandler 객체를 초기화한다.
        dbHandler = DBHandler(this,null,null,1)

        viewCustomers()

        //floating 버튼을 클릭하면 사용자 추가하는 화면으로 넘어간다.
        fab.onClick {
            startActivity<AddCustomerActivity>()
        }

        //editText의 문자열이 변경될 때마다 수행되는 이벤트
        //filteredCustomers라는 배열을 만든 후
        //customerslist의 소문자 이름과 사용자가 입력한 문자의 소문자를 비교한다.
        //포함이 되어 있다면 filteredCustomer에 넣어진다.
        //입력된 배열은 adapter에 저장되어 화면에 보여진다.
        editSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filteredCustomers = ArrayList<Customer>()
                if(!editSearch.text.isEmpty()){
                    for (i in 0..customerslist.size-1){
                        if(customerslist.get(i).customerName!!.toLowerCase().contains(s.toString().toLowerCase()))
                            filteredCustomers.add(customerslist[i])
                    }
                    adapter = CustomerAdapter(this@MainActivity,filteredCustomers)
                    rv.adapter = adapter
                }else{
                    adapter = CustomerAdapter(this@MainActivity,customerslist)
                    rv.adapter = adapter
                }
            }

        })


    }

    //db에 있는 customer데이터를 adapter에 연결한다.
    //recyclerView와 dapter를 연결하여 화면에 db에 저장된 데이터를 보여준다.
    private fun viewCustomers() {
        customerslist = dbHandler.getCustomers(this)
        adapter = CustomerAdapter(this, customerslist)
        rv = findViewById(R.id.rv)
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
