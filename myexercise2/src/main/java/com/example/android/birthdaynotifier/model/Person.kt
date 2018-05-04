package com.example.android.birthdaynotifier.model

import android.support.annotation.DrawableRes
import com.example.android.birthdaynotifier.R

data class Person(@DrawableRes var personIcon: Int, var personName: String, var personBirthday: String) {

}