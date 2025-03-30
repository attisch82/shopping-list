package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Category
import com.farkasatesz.feature_firestore.repository.CategoryRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class CategoryRepositoryImpl(
    firestore: FirebaseFirestore
) : CategoryRepository {

    val collection = firestore.collection("categories")

    override fun getCategoryByQuery(query: String) =
        FirestoreOperation.getByQuery<Category>(collection, "name", query)

    override fun getAll() =
        FirestoreOperation.getAll<Category>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<Category>(collection, id, Category("0", "error"))

    override suspend fun add(item: Category) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: Category) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: Category) =
        FirestoreOperation.delete(collection, item.id)

}