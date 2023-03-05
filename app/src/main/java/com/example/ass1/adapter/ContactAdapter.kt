package com.example.ass1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.ass1.R
import com.example.ass1.modle.Contact
import com.google.firebase.firestore.FirebaseFirestore

class ContactAdapter(context: Context, var contacts: ArrayList<Contact>) : ArrayAdapter<Contact>(context, 0, contacts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.contact_design, parent, false)
        }
        val contact = getItem(position)
        val name = itemView!!.findViewById<TextView>(R.id.name)
        val number = itemView!!.findViewById<TextView>(R.id.number)
        val address = itemView!!.findViewById<TextView>(R.id.address)
        val deleteBtn = itemView!!.findViewById<TextView>(R.id.btnDelete)
        if (contact != null) {
            name.text = contact.name
            number.text = contact.number
            address.text = contact.address
            deleteBtn.setOnClickListener {
                FirebaseFirestore.getInstance()
                    .collection("contacts")
                    .document(contact.id)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
                        contacts.removeAt(position)
                        notifyDataSetChanged()
                    }
            }
        }
        return itemView
    }
}
