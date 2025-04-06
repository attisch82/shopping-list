package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Item

interface ItemRepository : Repository<Item> {
    suspend fun getItemsByQuery(query: String): List<Item>
    suspend fun getItemsByCategory(categoryName: String): List<Item>
    suspend fun getItemsByBrand(brandName: String): List<Item>
}