package com.mobitant.myloginmodule.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private var ourInstance: Retrofit?= null
    val instance : Retrofit
    get(){
        if(ourInstance == null)
            ourInstance = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") //10.0.2.2는 에뮬레이터상에서 localhost주소
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()) //Response를 String형태로 받기
                //.addConverterFactory(GsonConverterFactory.create())  Response를 JSON형태로 받기
                .build()
        return ourInstance!!
    }
}