package com.mobitant.mysqlitecrudmodule

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_customers.view.*
import kotlinx.android.synthetic.main.item_customers_update.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class CustomerAdapter(mCtx: Context, val customers: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    val mCtx = mCtx

    //xml의 view가져오기
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCustomerName = itemView.txtCustomerName
        val txtMaxCredit = itemView.txtMaxCredit
        val txtPhoneNumber = itemView.txtPhoneNo
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
    //item 삭제 구현을 한다.
    override fun onBindViewHolder(p0: CustomerAdapter.ViewHolder, p1: Int) {
        val customer: Customer = customers[p1]
        p0.txtCustomerName.text = customer.customerName
        p0.txtMaxCredit.text = customer.maxCredit.toString()
        p0.txtPhoneNumber.text = customer.phoneNumber

        //삭제 버튼 이벤트
        //다이얼로그를 띄워서 삭제여부를 다시한번 더 묻는다.
        p0.btnDelete.onClick {
            val customerName = customer.customerName
            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Warning")
                .setMessage("Are You Sure to Delete : $customerName ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (MainActivity.dbHandler.deleteCustomer(customer.customerID)) {
                        customers.removeAt(p1)
                        notifyItemRemoved(p1)
                        notifyItemChanged(p1, customers.size)
                        Toast.makeText(mCtx, "Customer $customerName Deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(mCtx, "Error Deleting", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                .setIcon(R.drawable.ic_warning)
                .show()
        }

        //수정 버튼 이벤트
        //다이얼로그와 뷰를 연결하여 수정할 수 있는 창을 띄운다.
        p0.btnUpdate.onClick {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.item_customers_update,null)

            val txtCustName : TextView = view.editUpCustomerName
            val txtMaxCredit: TextView = view.editUpMaxCredit
            val txtNumber : TextView = view.editUpPhoneNumber

            txtCustName.text = customer.customerName
            txtMaxCredit.text = customer.maxCredit.toString()
            txtNumber.text = customer.phoneNumber

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Update Customer Info")
                .setView(view)
                .setPositiveButton("Update") { _, _ ->
                    val isUpdate = MainActivity.dbHandler.updateCustomer(
                        customer.customerID.toString(),
                        view.editUpCustomerName.text.toString(),
                        view.editUpMaxCredit.text.toString(),
                        view.editUpPhoneNumber.text.toString())

                    if (isUpdate){
                        customers[p1].customerName = view.editUpCustomerName.text.toString()
                        customers[p1].maxCredit = view.editUpMaxCredit.text.toString().toDouble()
                        customers[p1].phoneNumber = view.editUpPhoneNumber.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(mCtx,"Updated Successfull",Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(mCtx,"Error Updating",Toast.LENGTH_SHORT).show()
                    }
                }.setNegativeButton("Cancel") { _, _ ->  }
            val alert = builder.create()
            alert.show()
        }

    }
}