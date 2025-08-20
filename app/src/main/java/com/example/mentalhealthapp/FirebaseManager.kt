package com.example.mentalhealthapp

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseManager : Application() {

    companion object {
        lateinit var auth: FirebaseAuth
        lateinit var firestore: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        lateinit var analytics: FirebaseAnalytics
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Initialize Firebase services
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        analytics = FirebaseAnalytics.getInstance(this)
    }
}
