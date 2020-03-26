package com.ht117.demo.realm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val data: MutableList<UserModel> = mutableListOf()): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    fun display(models: List<UserModel>) {
        data.clear()
        data.addAll(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindView(data[position])
    }

    inner class UserHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindView(model: UserModel) {
            itemView.tvName.text = model.name
            itemView.tvOld.text = "${model.old}"
        }
    }
}