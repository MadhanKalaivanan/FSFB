package com.fsfb.module.homeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fsfb.R
import com.fsfb.module.dto.DataClass
import kotlinx.android.synthetic.main.list_row.view.*

class HomeScreenListAdapter(private val context: Context, private var data: ArrayList<DataClass>?) :
    RecyclerView.Adapter<HomeScreenListAdapter.ViewHolder>() {

    fun notifyHomeList(data: ArrayList<DataClass>?) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data?.get(position)!!, context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(data: DataClass, context: Context) {
            itemView.name.text = data.name?.first + data.name?.last
            itemView.age.text = "Age - " + data.dob?.age
            itemView.state.text = data.location?.state
            itemView.country.text = data.location?.country
            itemView.accept.text = if(data.isAccept) "Member Accepted" else "Accept"
            itemView.decline.text = if(data.isDeclined) "Member Declined" else "Decline"
            data.picture?.large?.let {
                Glide.with(context).load(it).into(itemView.img)
            }
            itemView.accept.setOnClickListener{
                if(!data.isAccept){
                    itemView.accept.text = "Member Accepted"
                    data.isAccept = true
                    (context as BtnClickListener).click(adapterPosition, ButtonState.ACCEPT)
                }
            }
            itemView.decline.setOnClickListener{
                if(!data.isDeclined){
                    itemView.decline.text = "Member Declined"
                    data.isDeclined = true
                    (context as BtnClickListener).click(adapterPosition, ButtonState.DECLINE)
                }
            }
        }
    }

}