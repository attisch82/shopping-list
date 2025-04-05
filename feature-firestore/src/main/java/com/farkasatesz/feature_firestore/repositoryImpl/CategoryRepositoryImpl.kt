package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Category
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.repository.CategoryRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class CategoryRepositoryImpl(
    firestore: FirebaseFirestore
): CategoryRepository,
    Repository<Category> by FirestoreRepositoryImpl(firestore, "categories") {

    private val collectionRef = firestore.collection("categories")
    private val customQuery = CustomQuery<Category> { query ->
        FirestoreOperation.getByQuery(collectionRef, "name", query)
    }

    override suspend fun getCategoryByQuery(query: String) = customQuery.query(query)
}