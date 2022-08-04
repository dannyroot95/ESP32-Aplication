package com.aukde.esp.Providers

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Network {

    val db : DatabaseReference = Firebase.database.reference

    fun database() : DatabaseReference{
        return db.child("Device").child("DHT11")
    }
}