package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.FirestoreModel
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepositoryImpl<T: FirestoreModel<T>>(
    firestore: FirebaseFirestore,
    collectionRef: String,
    clazz: Class<T>
) : Repository<T> {
    private val collection = firestore.collection(collectionRef)
    private val operation = FirestoreOperation<T>(collection, clazz)

    override suspend fun getAll() = operation.getAll()

    override suspend fun getById(id: String) = operation.getById(id)

    override suspend fun add(item: T) = operation.save(item)

    override suspend fun update(item: T, id: String) = operation.update(id, item)

    override suspend fun delete(item: T, id: String) = operation.delete(id)

}