package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Item
import com.farkasatesz.feature_firestore.repository.ItemRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ItemRepositoryImpl(
    firestore: FirebaseFirestore
) : ItemRepository,
    Repository<Item> by FirestoreRepositoryImpl(firestore, "items", Item::class.java)
{
    private val collection = firestore.collection("items")
    private val operation = FirestoreOperation<Item>(collection, Item::class.java)

    override suspend fun getItemsByQuery(query: String) = operation.getByQuery("name", query)

    override suspend fun getItemsByCategory(categoryName: String) = operation.getListByFieldName("categoryName", categoryName)

    override suspend fun getItemsByBrand(brandName: String) = operation.getListByFieldName("brand", brandName)
}