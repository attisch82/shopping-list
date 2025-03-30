package com.farkasatesz.feature_firestore.util.firestoreOperation

import android.util.Log
import com.farkasatesz.core.model.FirestoreModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

object FirestoreOperation {

    inline fun <reified T: FirestoreModel<T> > getAll(
        collection: CollectionReference
    ) = flow {
        val snapshot = collection.get().await()
        val list = snapshot.toObjects(T::class.java)
        emit(list)
    }.catch {
        emit(emptyList())
        Log.e("FirestoreOperation", "getAll: ${it.message}", it)
    }

    inline fun <reified T: FirestoreModel<T> > getById(
        collection: CollectionReference,
        id: String,
        errorItem: FirestoreModel<T>
    ) = flow {
        val snapshot = collection.document(id).get().await()
        val item = snapshot.toObject(T::class.java) ?: errorItem
        emit(item)
    }.catch {
        emit(errorItem)
        Log.e("FirestoreOperation", "getById: ${it.message}", it)
    }

    suspend inline fun <reified T: FirestoreModel<T> > save(
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

    suspend inline fun <reified T: FirestoreModel<T> > update(
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

    suspend inline fun <reified T: FirestoreModel<T> > delete(
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

    inline fun <reified T: FirestoreModel<T> > getByQuery(
        collection: CollectionReference,
        query: String
    ) = flow {
        val snapshot = collection
            .whereGreaterThanOrEqualTo("name", query.lowercase())
            .whereLessThan("name", query.lowercase() + '\uF8FF')
            .get()
            .await()
        val itemList = snapshot.documents.mapNotNull { document ->
            document.toObject(T::class.java)
        }
        emit(itemList)
    }.catch {
        Log.e("Impl", "getByQuery: ${it.message}", it)
        emit(emptyList())
    }
}