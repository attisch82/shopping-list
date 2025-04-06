package com.farkasatesz.feature_firestore.util.firestoreOperation

import android.util.Log
import com.farkasatesz.core.model.FirestoreModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FirestoreOperation<T>(
    private val collection: CollectionReference,
    private val clazz: Class<T>
) {

    suspend fun getAll(): List<T> {
        return runCatching {
            collection.get().await().toObjects<T>(clazz)
        }.onFailure {
            Log.e("FirestoreOperation", "getAll: ${it.message}", it)
        }.getOrDefault(emptyList<T>())
    }

    suspend fun getById(
        itemId: String
    ) : T? {
        return runCatching {
            collection.document(itemId).get().await().toObject(clazz)
        }.onFailure {
            Log.e("FirestoreOperation", "getById: ${it.message}", it)
        }.getOrDefault(null)
    }

    suspend fun save(
        item: FirestoreModel<T>
    ) : String? {
        return runCatching {
            val snapshot = collection.add(item.toMap()).await()
            snapshot.id
        }.onFailure {
            Log.e("FirestoreOperation", "save: ${it.message}", it)
            ""
        }.getOrDefault("")
    }

    suspend fun update(
        itemId: String,
        item: FirestoreModel<T>
    ) : Boolean {
        return runCatching {
            collection.document(itemId).set(item.toMap(), SetOptions.merge()).await()
            true
        }.onFailure {
            Log.e("FirestoreOperation", "update: ${it.message}", it)
            false
        }.getOrDefault(false)
    }

    suspend fun delete(
        itemId: String
    ) : Boolean {
        return runCatching {
            collection.document(itemId).delete().await()
            true
        }.onFailure {
            Log.e("FirestoreOperation", "delete: ${it.message}", it)
            false
        }.getOrDefault(false)
    }

    suspend fun getByQuery(
        fieldName: String,
        query: String
    ) : List<T> {
        return collection
            .whereGreaterThanOrEqualTo(fieldName, query.lowercase())
            .whereLessThan("name", query.lowercase() + '\uF8FF')
            .get()
            .await()
            .toObjects<T>(clazz)
    }

    suspend fun getSingleItemByFieldName(
        fieldName: String,
        fieldValue: String
    ): T {
        return collection
            .whereEqualTo(fieldName, fieldValue)
            .get()
            .await()
            .toObjects<T>(clazz)[0]
    }

    suspend fun getListByFieldName(
        fieldName: String,
        fieldValue: String,
    ) : List<T> {
        return collection
            .whereEqualTo(fieldName, fieldValue)
            .get()
            .await()
            .toObjects<T>(clazz)
    }
}