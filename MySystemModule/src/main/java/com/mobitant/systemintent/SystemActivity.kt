package com.mobitant.systemintent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_system.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.sendSMS

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system)

        call.setOnClickListener {
            makeCall(number = "01037797355")
        }

        message.setOnClickListener {
            sendSMS(number = "01037797355", text = "Hello, Kotlin!")
        }

        browse.setOnClickListener {
            browse(url = "https://google.com")
        }

        email.setOnClickListener {
            email(email = "heeheewuwu@gmail.com",subject = "Hello, Teaho kim", text = "How are you?")
        }



    }
}