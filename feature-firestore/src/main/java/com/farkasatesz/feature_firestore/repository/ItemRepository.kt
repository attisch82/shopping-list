package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Item
import com.google.firebase.firestore.QuerySnapshot

interface ItemRepository : Repository<Item> {
    suspend fun getItemsByQuery(query: String): QuerySnapshot
    suspend fun getItemsByCategory(categoryName: String): QuerySnapshot
    suspend fun getItemsByBrand(brandName: String): QuerySnapshot
}