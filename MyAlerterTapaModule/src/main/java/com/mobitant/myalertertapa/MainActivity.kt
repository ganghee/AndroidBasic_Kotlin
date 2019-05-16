package com.mobitant.myalertertapa

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tapadoo.alerter.Alerter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //버튼을 클릭하면 buyIphoneXS 메서드가 실행됩니다.
    fun buyIphoneXS(view: View){

        //title, text, 알림창의 버튼을 구현했습니다.
        //BUY 버튼을 클릭하면 그 위에 두 번째 알림창이 또 나옵니다.
        //두 번째 알림창에서 title, text, 배경색, textColor을 지정합니다.
        Alerter.create(this)
            .setTitle("Buy Laptop")
            .setText("Are you sure to buy now?")
            .addButton("BUY", R.style.AlertButton, View.OnClickListener {
                Alerter.create(this)
                    .setTitle("Success")
                    .setText("You just paid $250")
                    .setBackgroundColorInt(Color.parseColor("#1abc9c"))
                    .setTextAppearance(Color.parseColor("#34495e"))
                    .show()
            })
            .setBackgroundColorInt(Color.parseColor("#e74c3c"))
            .setIcon(R.drawable.ic_user)
            .show()
    }


}
