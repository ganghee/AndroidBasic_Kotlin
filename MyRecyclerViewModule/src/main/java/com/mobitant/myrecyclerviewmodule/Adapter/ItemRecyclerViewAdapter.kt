package com.mobitant.myrecyclerviewmodule.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mobitant.myrecyclerviewmodule.R
import com.mobitant.myrecyclerviewmodule.VO.CharacterItem

class ItemRecyclerViewAdapter (val ctx: Context, val dataList:ArrayList<CharacterItem>): RecyclerView.Adapter<ItemRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.recycler_item,viewGroup,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =
        dataList.size

    //배열에서 리스트를 가져와 holder에 넣어준다.
    override fun onBindViewHolder(holder:Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].img_url)
            .into(holder.img_item)
        holder.name.text = dataList[position].name
        holder.num_like.text = "♥" + dataList[position].num_like.toString()
        holder.status.text = dataList[position].status
    }

    //각각의 View들을 묶어주는 역할을 한다.
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img_item = itemView.findViewById(R.id.img_item) as ImageView
        var name = itemView.findViewById(R.id.txt_name) as TextView
        var num_like = itemView.findViewById(R.id.txt_numlike) as TextView
        var status = itemView.findViewById(R.id.txt_status) as TextView


    }

}