package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.ShoppingList
import com.farkasatesz.feature_firestore.repository.ShoppingListRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ShoppingListRepositoryImpl(
    firestore: FirebaseFirestore
) : ShoppingListRepository {
    val collection = firestore.collection("shoppingLists")
    override fun getListByQuery(query: String) =
        FirestoreOperation.getByQuery<ShoppingList>(collection, "name", query)

    override fun getShoppingListBySupermarket(supermarketId: String) =
        FirestoreOperation.getListByFieldName<ShoppingList>(
            collection,
            "supermarketId",
            supermarketId
        )

    override fun getAll() =
        FirestoreOperation.getAll<ShoppingList>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<ShoppingList>(collection, id, ShoppingList("0", "error"))

    override suspend fun add(item: ShoppingList) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: ShoppingList) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: ShoppingList) =
        FirestoreOperation.delete(collection, item.id)


}