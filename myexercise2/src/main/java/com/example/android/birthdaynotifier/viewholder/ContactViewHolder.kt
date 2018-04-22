package com.example.android.birthdaynotifier.viewholder

import android.support.design.widget.Snackbar
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.android.birthdaynotifier.R

internal class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cardView: CardView
    private val name: TextView

    init {

        // Find our views
        cardView = itemView.findViewById<View>(R.id.cardView) as CardView
        name = itemView.findViewById<View>(R.id.personName) as TextView

        // Show a Snackbar when the card view is clicked
        cardView.setOnClickListener { Snackbar.make(cardView, name.text.toString() + " clicked", Snackbar.LENGTH_LONG).show() }
    }

    // Here we set the new item model on our view holder and
    // update our views accordingly
    fun update(contactName: String) {
        name.text = contactName
    }
}