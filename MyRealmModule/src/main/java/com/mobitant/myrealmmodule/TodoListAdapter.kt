package com.mobitant.myrealmmodule

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

class TodoListAdapter (realmResult: OrderedRealmCollection<Todo>)
    : RealmBaseAdapter<Todo>(realmResult){

    //position:리스트 뷰의 아이템 위치
    //convertView: 재활용되는 아이템의 뷰
    //parent: 부모 뷰 즉 여기서는 리스트 뷰의 참조를 가리킨다.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh : ViewHolder
        val view : View

        //아이템 뷰가 없을 때 레이아웃을 작성
        if (convertView == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_todo,parent,false)
            vh = ViewHolder(view)   //뷰 홀더 객체 초기화
            view.tag = vh           //뷰 홀더 객체는 tag프로퍼티로 view에 저장

        //convertView가 null이 아니라면 이전에 작성했던 convertView를 재사용 한다.
        }else{
            view = convertView
            vh = view.tag as ViewHolder
        }

        //adapterData 프로퍼티를 통해 데이터에 접근 한다.
        //값이 있다면 해당 위치의 데이터를 item변수에 담는다.
        //할 일 텍스트와 날짜를 각각 텍스트 뷰에 표시 합니다.
        if (adapterData != null){
            val item = adapterData!![position]
            vh.textTextView.text = item.title
            vh.dateTextView.text = DateFormat.format("yyyy/MM/dd", item.date)
        }

        return view
    }

    //xml의 뷰들을 캡슐화 시킨것이다.
    class ViewHolder(view: View){
        val dateTextView : TextView = view.findViewById(R.id.text1)
        var textTextView : TextView = view.findViewById(R.id.text2)
    }

}