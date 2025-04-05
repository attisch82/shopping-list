package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.Supermarket
import com.google.firebase.firestore.QuerySnapshot

interface SupermarketRepository : Repository<Supermarket> {
    suspend fun getSupermarketByQuery(query: String): QuerySnapshot
}