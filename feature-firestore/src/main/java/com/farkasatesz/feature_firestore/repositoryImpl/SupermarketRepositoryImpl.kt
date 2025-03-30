package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Supermarket
import com.farkasatesz.feature_firestore.repository.SupermarketRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class SupermarketRepositoryImpl(
    firestore: FirebaseFirestore
) : SupermarketRepository {
    val collection = firestore.collection("supermarkets")
    override fun getSupermarketByQuery(query: String) =
        FirestoreOperation.getByQuery<Supermarket>(collection, "name", query)

    override fun getAll() =
        FirestoreOperation.getAll<Supermarket>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<Supermarket>(collection, id, Supermarket("0", "error"))

    override suspend fun add(item: Supermarket) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: Supermarket) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: Supermarket) =
        FirestoreOperation.delete(collection, item.id)
}