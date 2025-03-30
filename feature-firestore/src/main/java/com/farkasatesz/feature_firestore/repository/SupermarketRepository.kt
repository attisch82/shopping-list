package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Supermarket
import kotlinx.coroutines.flow.Flow

interface SupermarketRepository : Repository<Supermarket> {
    fun getSupermarketByQuery(query: String): Flow<List<Supermarket>>
}