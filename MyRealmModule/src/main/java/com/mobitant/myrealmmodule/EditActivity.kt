package com.mobitant.myrealmmodule

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivity : AppCompatActivity() {

    //Realm 데이터 베이스 초기화
    val realm = Realm.getDefaultInstance()
    //오늘 날짜로 Calender 객체 가져오기
    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //업데이트 조건
        //id가 -1이면 추가 모드이고 아니면 수정 모드이다.
        val id = intent.getLongExtra("id", -1L)
        if (id == -1L) {
            insertMode()
        } else {
            updateMode(id)
        }

        //현재 객체의 특정 필드를 다른 값으로 설정한다
        //오늘 날짜로 변경된다.
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    //추가 모드 초기화
    private fun insertMode() {
        //삭제 버튼 감추기
        deleteFab.visibility = View.GONE

        //완료 버튼을 클릭하면 추가
        doneFab.setOnClickListener {
            insertTodo()
        }
    }

    //수정 모드 초기화
    private fun updateMode(id: Long) {

        //id에 해당하는 데이터 객체를 가져와 뷰에 보여준다.
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        //완료버튼을 누르면 수정
        doneFab.setOnClickListener {
            updateTodo(id)
        }

        //삭제버튼을 누르면 삭제
        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    //기본키가 1씩 증가하는 코드
    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }

    //입력 구현 코드
    //입력한 데이터가 객체에 저장
    private fun insertTodo() {
        realm.beginTransaction()    //트랜잭션 시작

        val newItem = realm.createObject<Todo>(nextId())     //새 객체 생성
        newItem.title = todoEditText.text.toString()                //새 객체에 값 입력
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()       //트랜잭션 종료 반영

        alert("내용이 추가되었습니다.") {
            yesButton { finish() }
        }.show()
    }

    //수정 구현 코드
    //id가 일치하는 객체 수정
    private fun updateTodo(id: Long) {
        realm.beginTransaction()

        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert("내용이 변경되었습니다.") {
            yesButton { finish() }
        }.show()
    }

    //삭제 구현 코드
    //id가 일치하는 객체 삭제
    private fun deleteTodo(id: Long) {
        realm.beginTransaction()
        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        deleteItem.deleteFromRealm()        //객체 삭제
        realm.commitTransaction()

        alert("내용이 삭제 되었습니다.") {
            yesButton { finish() }
        }.show()
    }
}
