package com.mobitant.mysearchmodule.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit?= null

    //싱글톤 방식으로 Retrofit객체를 생성한다.
    fun getInstance(): Retrofit{
        if(instance == null)
            instance = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")                   //3000번 포트번호로 Url을 가져온다.
                .addConverterFactory(GsonConverterFactory.create())         //JSON형식으로 가져온다.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //RxJava에서 사용하는 observable객체를 리턴한다.
                .build()
        return instance!!

    }
}