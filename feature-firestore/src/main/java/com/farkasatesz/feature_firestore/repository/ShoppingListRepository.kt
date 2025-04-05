package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.ShoppingList
import com.google.firebase.firestore.QuerySnapshot

interface ShoppingListRepository : Repository<ShoppingList> {
    suspend fun getListByQuery(query: String): QuerySnapshot
    suspend fun getShoppingListBySupermarket(supermarketId: String): QuerySnapshot
}