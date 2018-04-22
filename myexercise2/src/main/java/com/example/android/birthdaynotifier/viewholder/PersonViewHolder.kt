package com.example.android.birthdaynotifier.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android.birthdaynotifier.R

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var icon: ImageView = view.findViewById(R.id.personIcon)
    var name: TextView = view.findViewById(R.id.personName)
    var birthday: TextView? = view.findViewById(R.id.personBirthday)
}