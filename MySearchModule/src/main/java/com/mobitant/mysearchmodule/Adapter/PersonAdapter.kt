package com.mobitant.mysearchmodule.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.mobitant.mysearchmodule.Interface.IPersonClickListener
import com.mobitant.mysearchmodule.Model.Person
import com.mobitant.mysearchmodule.R

class PersonAdapter (internal var context: Context, internal var personList:List<Person>): RecyclerView.Adapter<PersonAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.person_layout,p0,false)
        return MyViewHolder(itemView)
    }

    //받아온 데이터 List의 갯수를 반환합니다.
    override fun getItemCount(): Int {
        return personList.size
    }

    //Holder라는 틀에 List의 정보를 넣어줍니다.
    //특별한 반복문 없이 getItemCount()에서 지정한 값만큼 Holder가 생성됩니다.
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.name.text = personList[p1].name
        p0.email.text = personList[p1].email
        p0.phone.text = personList[p1].phone

        //홀수 번째 item들의 배경색을 지정합니다.
        if (p1 % 2 == 0)
            p0.root_view.setCardBackgroundColor(Color.parseColor("#e1e1e1"))

        //item이 클릭하였을때 이름을 toast로 띄웁니다.
        p0.setClick(object:IPersonClickListener{
            override fun onPersonClick(view: View, position: Int) {
                Toast.makeText(context,""+personList[position].name,Toast.LENGTH_LONG).show()
            }
        })

    }

    //findViewById를 이용하여 여러개의 View들을 하나의 item으로 만듭니다.
    //이 item의 객체를 MyViewHolder 라고 합니다.
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{

        internal var root_view: CardView
        internal var name: TextView
        internal var email: TextView
        internal var phone : TextView

        private lateinit var personClickListener: IPersonClickListener

        fun setClick(personClickListener: IPersonClickListener)
        {
            this.personClickListener = personClickListener
        }

        init {
            root_view = itemView.findViewById(R.id.root_view) as CardView
            name  = itemView.findViewById(R.id.text_name) as TextView
            email = itemView.findViewById(R.id.text_email) as TextView
            phone = itemView.findViewById(R.id.text_phone) as TextView

        }
        override fun onClick(p0: View?) {
            personClickListener.onPersonClick(p0!!,adapterPosition)
        }
    }
}