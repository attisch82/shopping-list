package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository : Repository<Category> {
    fun getCategoryByQuery(query: String): Flow<List<Category>>
}