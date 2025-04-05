package com.farkasatesz.feature_firestore.contracts

import com.google.firebase.firestore.QuerySnapshot

fun interface CustomQuery<T> {
    suspend fun query(query: String): QuerySnapshot
}