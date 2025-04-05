package com.farkasatesz.feature_firestore.contracts

import com.google.firebase.firestore.DocumentSnapshot

fun interface FindOne <T> {
    suspend fun by(id: String): DocumentSnapshot
}