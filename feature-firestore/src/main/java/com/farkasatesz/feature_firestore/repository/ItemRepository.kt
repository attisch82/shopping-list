package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository : Repository<Item> {
    fun getItemByQuery(query: String): Flow<List<Item>>
    fun getItemsByCategory(categoryName: String): Flow<List<Item>>
    fun getItemsByBrand(brandName: String): Flow<List<Item>>
}