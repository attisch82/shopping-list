package com.farkasatesz.feature_firestore.util.firestoreOperation

import android.util.Log
import com.farkasatesz.core.model.FirestoreModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

object FirestoreOperation {

    suspend fun getAll(
        collection: CollectionReference
    ): QuerySnapshot {
        return collection.get().await()
    }

    suspend fun getById(
        collection: CollectionReference,
        id: String,
    ): DocumentSnapshot {
        return collection.document(id).get().await()
    }

    suspend fun <T: FirestoreModel<T>> save(
        collection: CollectionReference,
        item: T
    ) : String? {
        return runCatching {
            val snapshot = collection.add(item.toMap()).await()
            snapshot.id
        }.onFailure {
            Log.e("FirestoreOperation", "save: ${it.message}", it)
            ""
        }.getOrDefault("")
    }

    suspend fun <T: FirestoreModel<T> > update(
        collection: CollectionReference,
        itemId: String,
        item: T
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
        collection: CollectionReference,
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
        collection: CollectionReference,
        fieldName: String,
        query: String
    ) : QuerySnapshot {
        return collection
            .whereGreaterThanOrEqualTo(fieldName, query.lowercase())
            .whereLessThan("name", query.lowercase() + '\uF8FF')
            .get()
            .await()
    }

    suspend fun getListByFieldName(
        collection: CollectionReference,
        fieldName: String,
        fieldValue: String
    ) : QuerySnapshot {
        return collection
            .whereEqualTo(fieldName, fieldValue)
            .get()
            .await()
    }
}