package com.example.android.birthdaynotifier.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.birthdaynotifier.R
import com.example.android.birthdaynotifier.model.Person
import com.example.android.birthdaynotifier.viewholder.PersonViewHolder

class PersonAdapter(val mContext: Context, var personsList: ArrayList<Person>) : RecyclerView.Adapter<PersonViewHolder>() {

    //1. this method is called, to know how many items to display
    override fun getItemCount(): Int {
        return personsList.size
    }

    //2. show exact items, which have space on the screen
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PersonViewHolder {

        val itemView: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_contact, parent, false)
        return PersonViewHolder(itemView)
    }

    //3. sets data to view
    override fun onBindViewHolder(holder: PersonViewHolder?, position: Int) {

        var person: Person = personsList.get(position)
        holder?.icon?.setImageResource(person.personIcon)
        holder?.name?.text = person.personName
        holder?.birthday?.text = person.personBirthday
    }

    fun setPersonList(contactList: ArrayList<Person>) {
        this.personsList = contactList
        notifyDataSetChanged()
    }
}