package com.example.ass1.modle

import com.google.firebase.firestore.DocumentId

data class Contact(
    @DocumentId var id:String,
    var name: String? = "",
    var number: String? = "",
    var address: String? = ""
)
