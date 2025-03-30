package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository : Repository<ShoppingList> {
    fun getListByQuery(query: String): Flow<List<ShoppingList>>
    fun getShoppingListBySupermarket(supermarketId: String): Flow<List<ShoppingList>>
}