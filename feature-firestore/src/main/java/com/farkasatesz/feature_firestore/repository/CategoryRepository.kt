package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Category
import com.google.firebase.firestore.QuerySnapshot

interface CategoryRepository : Repository<Category> {
    suspend fun getCategoryByQuery(query: String): QuerySnapshot
}