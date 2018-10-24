package com.algonquinlive.dall0078.midtermcontactsapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val goToContacts = findViewById(R.id.button) as? Button
        goToContacts?.setOnClickListener {

            val intent = Intent(this, ContactsListActivity::class.java)
            startActivity(intent)
        }
    }


}
