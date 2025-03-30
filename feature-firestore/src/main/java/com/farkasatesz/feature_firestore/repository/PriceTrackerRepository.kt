package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.PriceTracker
import kotlinx.coroutines.flow.Flow

interface PriceTrackerRepository : Repository<PriceTracker> {
    fun getPricesByShoppingItem(shoppingItemId: String): Flow<List<PriceTracker>>
    suspend fun getLastPrice(shoppingItemId: String): Double
}