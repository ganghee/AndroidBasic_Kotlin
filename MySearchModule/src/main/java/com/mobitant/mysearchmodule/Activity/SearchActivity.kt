package com.mobitant.mysearchmodule.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mobitant.mysearchmodule.API.ISearchAPI
import com.mobitant.mysearchmodule.API.RetrofitClient
import com.mobitant.mysearchmodule.Adapter.PersonAdapter
import com.mobitant.mysearchmodule.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity() {

    internal lateinit var myAPI: ISearchAPI
    internal var compositeDisposable = CompositeDisposable()
    internal lateinit var layoutManager: LinearLayoutManager
    internal lateinit var adapter : PersonAdapter
    internal var suggestList:MutableList<String> = ArrayList()

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }


    private val api:ISearchAPI

    //Retrofit객체에 API인터페이스를 지정한다
    //그리고 이 함수가 바로 실행
    get() = RetrofitClient.getInstance().create(ISearchAPI::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        myAPI =api

        //View
        //각 item들이 RecyclerView의 전체 크기를 변경하지 않는다면
        //setHasFixedSize() 함수를 사용해서
        //변경될 가능성이 있다면 false, 없다면 true
        recycler_search.setHasFixedSize(true)

        //layout객체를 받아 recyclerView의 DividerItemDecoration함수를 통해
        //구분선을 구현할 수 있다.
        layoutManager = LinearLayoutManager(this)
        recycler_search.layoutManager = layoutManager
        recycler_search.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))

        //search_bar의 CardView그림자 구현하기
        search_bar.setCardViewElevation(10)

        //search_bar의 검색목록 보여주는 함수
        addSuggestList()

        //search_bar에서 입력되는 text가 바뀔때마다 실행되는 이벤트
        search_bar.addTextChangeListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            //입력되는 문자열은 대소문자를 구분하여 나타내지만 검색 목록에는 문자열을 모두
            //소문자로 바꾸어서 suggestList에 있는 소문자들과 비교합니다
            //일치하면 search_bar에 넣어 검색목록에 보여줍니다.
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val suggest=ArrayList<String>()
                for(search_term in suggestList)
                    if(search_term.toLowerCase().contentEquals(search_bar.text.toLowerCase()))
                        suggest.add(search_term)
                search_bar.lastSuggestions = suggest
            }

        })

        //검색 버튼을 눌렀을 때
        search_bar.setOnSearchActionListener(object:MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {
            }

            //검색 창에 아무것도 바뀌지 않았을 때 getAllPerson()실행
            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    getAllPerson()
            }

            //검색을 실행 하였을 때 text값을 받아 검색 실행
            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })

        //초기화 작업- 모든 사람의 정보를 가져옴
        getAllPerson()

    }

    //myAPI 인터페이스에 검색창의 문자열을 서버에 보냅니다.
    //PersonAdapter에 서버에서 응답한 데이터가 전달됩니다.
    //people변수로 응답을 받고 이는 Adapter에서 데이터 형식을 지정하였습니다.
    //데이터 형식은 List<Person> 입니다.
    //recycler_search를 통해 데이터가 보여지게 됩니다.
    private fun startSearch(query: String) {
        compositeDisposable.addAll(myAPI.searchPerson(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ people ->
                adapter = PersonAdapter(baseContext,people)
                recycler_search.adapter = adapter
            },{
                toast("Not found").show()
            }
            ))
    }

    //DB에 저장되어 있는 모든 데이터를 client에 보여줍니다.
    private fun getAllPerson() {
        compositeDisposable.addAll(myAPI.personList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ people ->
                adapter = PersonAdapter(baseContext,people)
                recycler_search.adapter = adapter
            },{
                toast("Not found").show()
            }
        ))
    }

    //search_bar의 검색 목록 보여주는 함수
    //suggestList의 자료형은 변할 수 있는 배열 MutableList<> 입니다.
    private fun addSuggestList() {
        suggestList.add("Eddy")
        suggestList.add("Tom")
        suggestList.add("Micheal")
        suggestList.add("Gandalf")

        search_bar.lastSuggestions = (suggestList)
    }


}
