package com.mobitant.myrxjavaretrofit2modeul.API

import com.mobitant.myrxjavaretrofit2modeul.Model.Post
import io.reactivex.Observable
import retrofit2.http.GET

//IMyAPI.posts를 설정하면 서버에서 response를 통해 데이터를 가져옵니다.
interface IMyAPI {
    @get:GET("posts")
    val posts: Observable<List<Post>>
}