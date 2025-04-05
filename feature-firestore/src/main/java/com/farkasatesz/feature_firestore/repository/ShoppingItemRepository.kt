package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.ShoppingItem
import com.google.firebase.firestore.QuerySnapshot

interface ShoppingItemRepository : Repository<ShoppingItem> {
    suspend fun getItemsWithQuantityMapped(shoppingListId: String): QuerySnapshot
}