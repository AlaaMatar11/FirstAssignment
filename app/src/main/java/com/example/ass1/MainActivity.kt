 package com.example.ass1


 import android.app.ProgressDialog
 import android.content.Intent
 import android.os.Bundle
 import android.widget.ArrayAdapter
 import android.widget.LinearLayout
 import android.widget.Toast
 import androidx.appcompat.app.AppCompatActivity
 import com.example.ass1.adapter.ContactAdapter
 import com.example.ass1.databinding.ActivityMainBinding
 import com.example.ass1.modle.Contact
 import com.google.firebase.database.*
 import com.google.firebase.firestore.FirebaseFirestore

 class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
   binding = ActivityMainBinding.inflate(layoutInflater)
   super.onCreate(savedInstanceState)
   setContentView(binding.root)

   binding.saveButton.setOnClickListener {
    uploadContact()
   }
  }

  private fun uploadContact() {
   val database = FirebaseFirestore.getInstance()


   val name = binding.nameEditText.text.toString()
   val number = binding.numberEditText.text.toString()
   val address = binding.addressEditText.text.toString()

   val contact = Contact(name, number, address)

   val progressDialog = ProgressDialog(this)
   progressDialog.setMessage("Uploading contact...")
   progressDialog.show()

   database.collection("contacts")
    .add(contact)
    .addOnSuccessListener {
     progressDialog.dismiss()
     Toast.makeText(this, "Contact uploaded successfully", Toast.LENGTH_SHORT).show()
     startActivity(Intent(this,ShowContacts::class.java))
    }
    .addOnFailureListener {
     Toast.makeText(this, "Failed to upload contact", Toast.LENGTH_SHORT).show()
     progressDialog.dismiss()
    }
  }
 }
