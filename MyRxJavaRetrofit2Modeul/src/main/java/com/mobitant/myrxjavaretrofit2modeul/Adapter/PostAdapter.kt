package com.mobitant.myrxjavaretrofit2modeul.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mobitant.myrxjavaretrofit2modeul.Model.Post
import com.mobitant.myrxjavaretrofit2modeul.R

class PostAdapter (internal var context: Context, internal var postList:List<Post>)
    : RecyclerView.Adapter<PostViewHolder>()
{
    //하나의 item을 구성한 layout의
    //view들을 ViewHoler에 넘겨 줍니다.
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.post_layout,p0,false)

        return PostViewHolder(itemView)
    }

    //List의 크기를 반환합니다.
    override fun getItemCount(): Int {
        return postList.size
    }

    //List의 데이터를 ViewHolder에 넣어줍니다.
    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.text_title.text = postList[p1].title
        p0.text_author.text = postList[p1].userId.toString()
        p0.text_content.text = StringBuilder(postList[p1].body.substring(0,20))
            .append("...").toString()
    }
}