package com.mobitant.myandroidbasic_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_alert.*
import org.jetbrains.anko.*

class AlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        toast_btn.setOnClickListener {
            toast("토스트")
        }

        alertDialog_btn.setOnClickListener {
            alert(title = "Message", message = "Let's learn Kotlin!"){
                positiveButton("Yes"){
                    toast("Yay!")
                }
                negativeButton("No"){
                    toast("No way...")
                }
            }.show()
        }

        select_dialog_btn.setOnClickListener {
            //다이얼로그에 표시할 목록을 생성한다.
            val cities = listOf("Seoul","Tokyo","Mountain View","Singapore")

            //리스트 다이얼로그를 생성하고 표시한다.
            selector("Select City",cities){
                    dialogInterface, i ->  toast("You selected ${cities[i]}!")
            }
        }

            //50에서 멈추게 된다.
            progressbar.setOnClickListener {
            val pd = progressDialog(title="File Download",message = "Downloading...")
            pd.show()
            pd.progress = 50
            indeterminateProgressDialog (message = "Please wait..." ).show()
        }
    }

}