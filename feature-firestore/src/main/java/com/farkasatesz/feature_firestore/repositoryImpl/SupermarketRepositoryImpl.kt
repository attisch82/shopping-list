package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Supermarket
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.repository.SupermarketRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class SupermarketRepositoryImpl(
    firestore: FirebaseFirestore
) : SupermarketRepository,
    Repository<Supermarket> by FirestoreRepositoryImpl(firestore, "supermarkets")
{
    private val collection = firestore.collection("supermarkets")
    private val customQuery = CustomQuery<Supermarket> { query ->
        FirestoreOperation.getByQuery(collection, "name", query)
    }

    override suspend fun getSupermarketByQuery(query: String) = customQuery.query(query)
}