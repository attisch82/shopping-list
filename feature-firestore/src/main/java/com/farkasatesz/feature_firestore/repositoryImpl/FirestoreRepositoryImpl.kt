package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.FirestoreModel
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepositoryImpl<T: FirestoreModel<T>>(
    firestore: FirebaseFirestore,
    collectionRef: String
) : Repository<T> {
    private val collection = firestore.collection(collectionRef)

    override suspend fun getAll() = FirestoreOperation.getAll(collection)

    override suspend fun getById(id: String) = FirestoreOperation.getById(
        collection,
        id
    )

    override suspend fun add(item: T) = FirestoreOperation.save(collection, item)

    override suspend fun update(item: T, id: String) = FirestoreOperation.update(collection, id, item)

    override suspend fun delete(item: T, id: String) = FirestoreOperation.delete(collection, id)

}