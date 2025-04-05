package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.BaseList
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.repository.BaseListRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class BaseListRepositoryImpl(
    firestore: FirebaseFirestore
) : BaseListRepository,
    Repository<BaseList> by FirestoreRepositoryImpl(firestore, "baseLists")
{
    private val collection = firestore.collection("baseLists")
    private val customQuery = CustomQuery<BaseList> { query ->
        FirestoreOperation.getByQuery(collection, "name", query)
    }
    override suspend fun getListByQuery(query: String) = customQuery.query(query)
}