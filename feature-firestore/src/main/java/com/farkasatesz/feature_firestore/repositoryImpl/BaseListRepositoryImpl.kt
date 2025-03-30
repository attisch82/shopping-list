package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.BaseList
import com.farkasatesz.feature_firestore.repository.BaseListRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class BaseListRepositoryImpl(
    firestore: FirebaseFirestore
) : BaseListRepository {
    val collection = firestore.collection("baseLists")
    override fun getListByQuery(query: String) =
        FirestoreOperation.getByQuery<BaseList>(collection, "name", query)

    override fun getAll() =
        FirestoreOperation.getAll<BaseList>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<BaseList>(collection, id, BaseList("0", "error"))

    override suspend fun add(item: BaseList) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: BaseList) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: BaseList) =
        FirestoreOperation.delete(collection, item.id)
}