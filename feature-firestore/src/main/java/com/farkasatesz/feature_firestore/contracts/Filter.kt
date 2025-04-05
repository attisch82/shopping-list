package com.farkasatesz.feature_firestore.contracts

import com.google.firebase.firestore.QuerySnapshot

fun interface Filter <T> {
    suspend fun by(fieldName: String, value: String) : QuerySnapshot
}