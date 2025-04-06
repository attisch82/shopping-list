package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.PriceTracker

interface PriceTrackerRepository : Repository<PriceTracker> {
    suspend fun getPricesByShoppingItem(shoppingItemId: String): List<PriceTracker>
    suspend fun getLastPrice(shoppingItemId: String): Double
}