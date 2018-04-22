package com.example.android.birthdaynotifier.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.android.birthdaynotifier.R
import com.example.android.birthdaynotifier.adapter.PersonAdapter
import com.example.android.birthdaynotifier.model.Person
import java.sql.Array
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapter: PersonAdapter? = null
    var emptyText: TextView? = null
    val personList: ArrayList<Person>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        emptyText = findViewById(R.id.empty_text)
        adapter = PersonAdapter(this, personList!!)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = this.adapter

        fab.setOnClickListener {

            Snackbar.make(fab, "Kontakte geladen", Snackbar.LENGTH_LONG).show()
            onContactsLoaded()
        }
    }


    private fun onContactsLoaded() {


        var contactList: ArrayList<Person> = ArrayList()
        contactList.add(Person(R.id.personIcon, "Max Mustermann", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Maria Musterfrau", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Stefan Müller", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Theresa Huber", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Manuel Bauer", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Stefanie Müller", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Hansi Hansmann", "01.02.1970"))
        contactList.add(Person(R.id.personIcon, "Max Mustermann", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Maria Musterfrau", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Stefan Müller", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Theresa Huber", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Manuel Bauer", "01.09.1999"))
        contactList.add(Person(R.id.personIcon, "Stefanie Müller", "01.09.1999"))

        emptyText?.visibility = View.GONE

        adapter?.setPersonList(contactList)
    }
}
