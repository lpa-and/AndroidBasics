package com.example.android.birthdaynotifier.model

import com.example.android.birthdaynotifier.R

class Person {

    var personIcon: Int = 0
    var personName: String = "Max Mustermann"
    var personBirthday: String = "01.01.1999"

    constructor(personIcon: Int, personName: String, personBirthday: String) {
        this.personIcon = personIcon
        this.personName = personName
        this.personBirthday = personBirthday
    }
}