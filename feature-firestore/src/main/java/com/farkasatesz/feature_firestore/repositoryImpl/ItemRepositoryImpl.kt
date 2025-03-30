package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Item
import com.farkasatesz.feature_firestore.repository.ItemRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ItemRepositoryImpl(
    firestore: FirebaseFirestore
) : ItemRepository {
    val collection = firestore.collection("items")
    override fun getItemByQuery(query: String) =
        FirestoreOperation.getByQuery<Item>(collection, "name", query)

    override fun getItemsByCategory(categoryName: String) =
        FirestoreOperation.getListByFieldName<Item>(collection, "categoryName", categoryName)

    override fun getItemsByBrand(brandName: String) =
        FirestoreOperation.getListByFieldName<Item>(collection, "brand", brandName)

    override fun getAll() =
        FirestoreOperation.getAll<Item>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<Item>(collection, id, Item("0", "error"))

    override suspend fun add(item: Item) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: Item) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: Item) =
        FirestoreOperation.delete(collection, item.id)

}