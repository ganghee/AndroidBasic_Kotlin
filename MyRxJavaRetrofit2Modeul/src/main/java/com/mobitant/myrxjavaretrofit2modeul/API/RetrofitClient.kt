package com.mobitant.myrxjavaretrofit2modeul.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit은 싱글톤으로 작성하였습니다.
//baseUrl을 통해 지성되 IP주소로 이동하고
//addConverterFactory로 Json형식으로 데이터를 받습니다.
//addCallAdapterFactory: RxJava에서 사용되는 observables 객체를 리턴 할 수 있도록 합니다.
object RetrofitClient {
    private var ourInstance : Retrofit?= null
    val instance : Retrofit
    get() {
        if (ourInstance == null) {
            ourInstance = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return ourInstance!!
    }
}