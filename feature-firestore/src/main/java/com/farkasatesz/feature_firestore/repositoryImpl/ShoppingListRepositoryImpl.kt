package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.ShoppingList
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.contracts.Filter
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.repository.ShoppingListRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ShoppingListRepositoryImpl(
    firestore: FirebaseFirestore
) : ShoppingListRepository,
    Repository<ShoppingList> by FirestoreRepositoryImpl(firestore, "shoppingLists")
{
    private val collection = firestore.collection("shoppingLists")
    private val customQuery = CustomQuery<ShoppingList> { query ->
        FirestoreOperation.getByQuery(collection, "name", query)
    }
    private val filter = Filter<ShoppingList> { fieldName, value ->
        FirestoreOperation.getListByFieldName(collection, fieldName, value)
    }

    override suspend fun getListByQuery(query: String) = customQuery.query(query)

    override suspend fun getShoppingListBySupermarket(supermarketId: String) =
        filter.by("supermarketId", supermarketId)
}