package com.farkasatesz.feature_firestore.repository

import kotlinx.coroutines.flow.Flow

interface Repository <T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: String): Flow<T>
    suspend fun add(item: T) : String?
    suspend fun update(item: T) : Boolean
    suspend fun delete(item: T) : Boolean
}