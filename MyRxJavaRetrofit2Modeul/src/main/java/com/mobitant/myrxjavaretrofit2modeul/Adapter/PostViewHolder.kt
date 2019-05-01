package com.mobitant.myrxjavaretrofit2modeul.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.post_layout.view.*

//ViewHolder는 view들을 모아둔 객체입니다.
class PostViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val text_author = itemView.textview_author
    val text_title = itemView.textview_title
    val text_content = itemView.textview_content
}