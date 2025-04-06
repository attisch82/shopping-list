package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.FirestoreModel

interface Repository<T: FirestoreModel<T>> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: String): T?
    suspend fun add(item: T) : String?
    suspend fun update(item: T, id: String) : Boolean
    suspend fun delete(item: T, id: String) : Boolean
}