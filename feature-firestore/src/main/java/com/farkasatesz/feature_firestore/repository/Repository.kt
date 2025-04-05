package com.farkasatesz.feature_firestore.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface Repository <T> {
    suspend fun getAll(): QuerySnapshot
    suspend fun getById(id: String): DocumentSnapshot
    suspend fun add(item: T) : String?
    suspend fun update(item: T, id: String) : Boolean
    suspend fun delete(item: T, id: String) : Boolean
}