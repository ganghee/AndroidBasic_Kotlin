package com.mobitant.mysqlitecrudmodule

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_customers.view.*

class CustomerAdapter( mCtx:Context, val customers : ArrayList<Customer>) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>(){

    val mCtx = mCtx

    //xml의 view가져오기
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtCustomerName = itemView.txtCustomerName
        val txtMaxCredit = itemView.txtMaxCredit
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }

    //반복적으로 나타낼 view 가져오기
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomerAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_customers, p0, false)
        return ViewHolder(v)

    }

    //배열의 크기 지정하기
    override fun getItemCount(): Int {
        return customers.size
        }

    //배열의 데이터를 view로 가져오기
    override fun onBindViewHolder(p0: CustomerAdapter.ViewHolder, p1: Int) {
        val customer : Customer = customers[p1]
        p0.txtCustomerName.text = customer.customerName
        p0.txtMaxCredit.text = customer.maxCredit.toString()
        }
}