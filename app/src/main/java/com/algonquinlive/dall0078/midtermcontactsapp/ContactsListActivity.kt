package com.algonquinlive.dall0078.midtermcontactsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class ContactsListActivity : Activity() {
    var contactListArray = ArrayList<Person>()

    data class Person(var firstName: String?, var lastName: String?, var phone: String?, var email: String?)


    lateinit var listAdapter:PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)

        var listItem = findViewById(R.id.displayListItems) as? ListView
        listItem?.setOnItemClickListener {_, _, position, _ ->

            val intent = Intent(this, ContactDetailsActivity::class.java)

            intent.putExtra("FirstName",contactListArray.get(position).firstName)
            intent.putExtra("LastName",contactListArray.get(position).lastName)
            intent.putExtra("Phone",contactListArray.get(position).phone)
            intent.putExtra("Email",contactListArray.get(position).email)

            startActivity(intent)
        }


        listAdapter = PersonAdapter(this)
        listItem?.setAdapter(listAdapter);

        var addContactsBtn = findViewById(R.id.addContacts) as? Button
        addContactsBtn?.setOnClickListener {

            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, 50)// should be startActivityForResult()
            onActivityResult(50, 2, intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            var contact = Person(
                    data?.getStringExtra("FirstName"),
                    data?.getStringExtra("LastName"),
                    data?.getStringExtra("PhoneNumber"),
                    data?.getStringExtra("Email")
            )
            contactListArray.add(contact)

            Log.i("onActResult",  contactListArray.get(0).firstName)


            listAdapter.notifyDataSetChanged()
        }

    }

    inner class PersonAdapter( ctx: Context) : ArrayAdapter<Person>(ctx, 0) {

        override fun getCount(): Int {
            return contactListArray.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var inflater = LayoutInflater.from(parent.context)
            var result: View
            result = inflater.inflate(R.layout.contact_list_view, parent, false)
            val individualFName = result.findViewById(R.id.listFirstName) as? TextView
            val individualLName = result.findViewById(R.id.listLastName) as? TextView

            val person = getItem(position)

            individualFName?.text = person.firstName
            individualLName?.text = person.lastName

            return result
        }

        override fun getItem(position: Int): Person {

            return contactListArray.get(position)
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}
