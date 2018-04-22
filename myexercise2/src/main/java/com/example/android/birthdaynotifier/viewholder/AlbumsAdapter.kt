package com.example.android.birthdaynotifier.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.android.birthdaynotifier.R
import com.example.android.birthdaynotifier.model.Person

/**
 * Created by Ravi Tamada on 18/05/16.
 */
class AlbumsAdapter(private val mContext: Context, private val albumList: List<Person>) : RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var count: TextView? = null
        var thumbnail: ImageView? = null
        var overflow: ImageView? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val album = albumList[position]
        holder.title!!.text = album.personName


        holder.overflow!!.setOnClickListener { }
    }


    override fun getItemCount(): Int {
        return albumList.size
    }
}