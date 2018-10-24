package com.algonquinlive.dall0078.midtermcontactsapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class AddContactActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val firstName = findViewById<EditText>(R.id.EditTextFirstName)
        val lastName = findViewById<EditText>(R.id.contactLastName)
        val phone = findViewById<EditText>(R.id.phoneNumber)
        val email = findViewById<EditText>(R.id.email)

        val acceptBtn = findViewById<Button>(R.id.accept)
        acceptBtn.setOnClickListener{
            val intent = Intent()

            intent.putExtra("FirstName",firstName.text.toString())
            intent.putExtra("LastName",lastName.text.toString())
            intent.putExtra("PhoneNumber", phone.text.toString())
            intent.putExtra("Email", email.text.toString())
            setResult(Activity.RESULT_OK, intent)

            Log.i("AddContactActivity", firstName.text.toString() + ", "
                    + lastName.text.toString() + ", "
                    + phone.text.toString() + ", "
                    + email.text.toString())
            finish()
        }

        var cancelBtn = findViewById<Button>(R.id.cancel)
        cancelBtn.setOnClickListener{

            finish()
        }
    }
}
