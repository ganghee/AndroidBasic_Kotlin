package com.mobitant.myrealmmodule

import android.app.Application
import io.realm.Realm

//Application(): 앱을 실행하면 가장 먼저 실행되는 애플리케이션 객체
//Realm객체를 초기화 한다.
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}