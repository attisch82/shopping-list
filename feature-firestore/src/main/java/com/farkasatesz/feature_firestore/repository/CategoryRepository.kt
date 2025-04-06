package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Category

interface CategoryRepository : Repository<Category> {
    suspend fun getCategoryByQuery(query: String): List<Category>
}