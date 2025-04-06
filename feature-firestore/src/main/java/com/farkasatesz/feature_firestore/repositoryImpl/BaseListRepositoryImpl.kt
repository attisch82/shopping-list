package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.BaseList
import com.farkasatesz.feature_firestore.repository.BaseListRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class BaseListRepositoryImpl(
    firestore: FirebaseFirestore
) : BaseListRepository,
    Repository<BaseList> by FirestoreRepositoryImpl(firestore, "baseLists", BaseList::class.java)
{
    private val collection = firestore.collection("baseLists")
    private val operation = FirestoreOperation<BaseList>(collection, BaseList::class.java)

    override suspend fun getListByQuery(query: String) = operation.getByQuery("name", query)
}