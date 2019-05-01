package com.mobitant.mysearchmodule.API

import com.mobitant.mysearchmodule.Model.Person
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ISearchAPI {
    //DB에 저장되어 있는 모든 person 정보를 가져온다.
    //myAPI.personList 이라는 정보를 client가 주면 GET방식의 person경로 서버요청을 한다.
    //List<Person>이라는 형식으로 받는다.
    @get:GET("person")
    val personList:Observable<List<Person>>

    //DB에 저장되어 있는 값 중에 파라미터 searchQuery와 일치하는 정보를 가져온다.
    //myAPI.searchPerson(query) 라는 정보를 client가 주면 POST방식의 search경로로 서버요청을 한다.
    //서버는 req.body.[search] 라는 정보를 받아야 한다. search는 @Field로 지정된 변수이다.
    //즉 @Field([변수]) 에서 변수는 서버가 받아야 하는 변수이다.
    @POST("search")
    @FormUrlEncoded
    fun searchPerson(@Field("search") searchQuery:String):Observable<List<Person>>



}