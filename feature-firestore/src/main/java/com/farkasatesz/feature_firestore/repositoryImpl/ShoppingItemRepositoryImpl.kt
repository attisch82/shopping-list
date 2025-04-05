package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.ShoppingItem
import com.farkasatesz.feature_firestore.contracts.Filter
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.repository.ShoppingItemRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ShoppingItemRepositoryImpl(
    firestore: FirebaseFirestore
) : ShoppingItemRepository,
    Repository<ShoppingItem> by FirestoreRepositoryImpl(firestore, "shoppingItems")
{
    private val collection = firestore.collection("shoppingItems")
    private val filter = Filter<ShoppingItem> { fieldName, value ->
        FirestoreOperation.getListByFieldName(collection, fieldName, value)
    }

    override suspend fun getItemsWithQuantityMapped(shoppingListId: String) =
        filter.by("shoppingListId", shoppingListId)
}