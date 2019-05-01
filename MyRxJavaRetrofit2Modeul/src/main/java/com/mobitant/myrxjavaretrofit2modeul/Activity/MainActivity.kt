package com.mobitant.myrxjavaretrofit2modeul.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mobitant.myrxjavaretrofit2modeul.API.IMyAPI
import com.mobitant.myrxjavaretrofit2modeul.API.RetrofitClient
import com.mobitant.myrxjavaretrofit2modeul.Adapter.PostAdapter
import com.mobitant.myrxjavaretrofit2modeul.Model.Post
import com.mobitant.myrxjavaretrofit2modeul.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //API는 늦은 초기화 선언만 하였다.
    //CompositeDisposable은 선언과 동시에 초기화를 수행한다.
    internal lateinit var jsonApi:IMyAPI
    internal  var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //init API
        //설정된 RetrofitClient 객체에 API 인터페이스를 구현한다.
        //이때 jsonApi가 초기화 된다.
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyAPI::class.java)

        //view설정
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()

    }

    //compositeDisposable을 통해 구독을 수행한다.
    //subscribe메소드의 첫 번째 파라미터는 정상적으로 수행되었을 때
    //두 번째 파라미터는 실패했을 때이다.
    private fun fetchData() {
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ companyList->
                displayData(companyList)
            },{
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            })
        )
    }

    //recyclerview에 Adapter구현
    private fun displayData(posts: List<Post>?) {
        val adapter2 = PostAdapter(this,posts!!)
        recycler_posts.adapter=adapter2
    }
    //compositeDisposable 객체를 모두 해체합니다.
    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }
}
