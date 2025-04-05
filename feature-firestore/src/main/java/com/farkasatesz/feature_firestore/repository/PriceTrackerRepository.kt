package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.PriceTracker
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface PriceTrackerRepository : Repository<PriceTracker> {
    suspend fun getPricesByShoppingItem(shoppingItemId: String): QuerySnapshot
    suspend fun getLastPrice(shoppingItemId: String): DocumentSnapshot
}