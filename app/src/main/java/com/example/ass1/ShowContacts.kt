package com.example.ass1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ass1.adapter.ContactAdapter
import com.example.ass1.databinding.ActivityShowContactsBinding
import com.example.ass1.modle.Contact
import com.google.firebase.firestore.FirebaseFirestore

class ShowContacts : AppCompatActivity() {
    private lateinit var binding: ActivityShowContactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityShowContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchContacts()
    }

    private fun fetchContacts() {
        val database = FirebaseFirestore.getInstance()

        database.collection("contacts")
            .get()
            .addOnSuccessListener {
                val contacts = ArrayList<Contact>()
                for (contactSnapshot in it) {
                    contacts.add(
                        Contact(contactSnapshot.id,contactSnapshot.getString("name")
                        ,contactSnapshot.getString("number"),contactSnapshot.getString("address"))
                    )
                }
                val adapter = ContactAdapter(this, contacts)
                binding.listView.adapter = adapter
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
    }
}