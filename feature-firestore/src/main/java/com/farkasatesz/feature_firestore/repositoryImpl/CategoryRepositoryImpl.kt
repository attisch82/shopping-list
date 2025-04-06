package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Category
import com.farkasatesz.feature_firestore.repository.CategoryRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class CategoryRepositoryImpl(
    firestore: FirebaseFirestore
): CategoryRepository,
    Repository<Category> by FirestoreRepositoryImpl(firestore, "categories", Category::class.java) {

    private val collectionRef = firestore.collection("categories")
    private val operation = FirestoreOperation<Category>(collectionRef, Category::class.java)

    override suspend fun getCategoryByQuery(query: String) = operation.getByQuery(
        fieldName = "name",
        query = query
    )
}