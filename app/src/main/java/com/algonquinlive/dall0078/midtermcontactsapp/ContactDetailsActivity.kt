package com.algonquinlive.dall0078.midtermcontactsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class ContactDetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        var phoneBtn = findViewById(R.id.phoneBtn) as? ImageButton
        var emailBtn = findViewById(R.id.emailBtn) as? ImageButton

        emailBtn?.setOnClickListener{

            var addresses = arrayOf(findViewById<EditText>(R.id.email2).text.toString())

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, addresses)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent) }

        }

        phoneBtn?.setOnClickListener{

            val phone = findViewById(R.id.phoneNumber2) as? EditText
            val phoneNumber = phone?.getText().toString()

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        onActivityResult(50, 2, intent)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        val fname = data?.getStringExtra("FirstName")
        val lname =data?.getStringExtra("LastName")
        val phoneNum = data?.getStringExtra("Phone")
        val email = data?.getStringExtra("Email")


        val editFname = findViewById(R.id.contactFirstName2) as? EditText
        val editLname = findViewById(R.id.contactLastName2) as? EditText
        val editPhone = findViewById(R.id.phoneNumber2) as? EditText
        val editEmail = findViewById(R.id.email2) as? EditText

        editFname?.setText(fname)
        editLname?.setText(lname)
        editPhone?.setText(phoneNum)
        editEmail?.setText(email)


    }
}
