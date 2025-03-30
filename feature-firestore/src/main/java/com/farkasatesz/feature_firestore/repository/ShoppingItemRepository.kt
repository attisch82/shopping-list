package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRepository : Repository<ShoppingItem> {
    fun getItemsWithQuantityMapped(shoppingListId: String): Flow<Map<String, Int>>
}