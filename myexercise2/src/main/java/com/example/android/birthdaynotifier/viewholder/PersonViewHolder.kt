package com.example.android.birthdaynotifier.viewholder

import android.support.design.widget.Snackbar
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android.birthdaynotifier.R

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var cardView: CardView
    var icon: ImageView
    var name: TextView
    var birthday: TextView

    init {

        cardView = view.findViewById(R.id.cardView)
        icon = view.findViewById(R.id.personIcon)
        name = view.findViewById(R.id.personName)
        birthday = view.findViewById(R.id.personBirthday)

        cardView.setOnClickListener { Snackbar.make(cardView, "${name.text.toString()} clicked", Snackbar.LENGTH_LONG).show()  }
    }
}