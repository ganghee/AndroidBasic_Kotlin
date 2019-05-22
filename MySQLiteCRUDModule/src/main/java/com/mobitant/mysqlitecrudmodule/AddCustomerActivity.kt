package com.mobitant.mysqlitecrudmodule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_customer.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class AddCustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        //저장버튼을 눌렀을 때 이벤트
        //입력된 데이터가 Model객체 형식으로 저장된다.
        //Model형식은 dbHandler객체의 addCustomer메소드로 db에 저장된다.
        btnSave.onClick{
            if(editCustomerName.text.isEmpty()){
                toast("Enter Customer Name").show()
                editCustomerName.requestFocus()
            }else
            {
                val customer = Customer()
                customer.customerName = editCustomerName.text.toString()

                if(editMaxCredit.text.isEmpty())
                    customer.maxCredit = 0.0
                else
                    customer.maxCredit = editMaxCredit.text.toString().toDouble()

                MainActivity.dbHandler.addCustomer(this@AddCustomerActivity,customer)
                clearEdits()
                editCustomerName.requestFocus()
            }
        }

        //취소 버튼을 누르면 입력 데이터가 없어진다.
        btnCancel.onClick {
            clearEdits()
            finish()
        }
    }

    //이름과 비용의 입력한 문자들을 지운다.
    private fun clearEdits(){
        editCustomerName.text.clear()
        editMaxCredit.text.clear()
    }
}
