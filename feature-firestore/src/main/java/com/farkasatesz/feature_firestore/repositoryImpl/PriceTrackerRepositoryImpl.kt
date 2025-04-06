package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Price
import com.farkasatesz.core.model.PriceTracker
import com.farkasatesz.feature_firestore.repository.PriceTrackerRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PriceTrackerRepositoryImpl(
    firestore: FirebaseFirestore
) : PriceTrackerRepository,
    Repository<PriceTracker> by FirestoreRepositoryImpl(firestore, "priceTrackers", PriceTracker::class.java)
{
    private val collection = firestore.collection("priceTrackers")
    private val operation = FirestoreOperation<PriceTracker>(collection, PriceTracker::class.java)

    override suspend fun getPricesByShoppingItem(shoppingItemId: String): List<PriceTracker> =
        operation.getListByFieldName("shoppingItemId", shoppingItemId)

    override suspend fun getLastPrice(shoppingItemId: String) : Double {
        val prices = getPricesByShoppingItem(shoppingItemId)
        val latestPrice = prices.maxByOrNull { it.savedAt }
        return latestPrice?.priceId?.let {
            val price = collection.document(it).get().await().toObject(Price::class.java)
            price?.price ?: 0.0
        }?: 0.0
    }
}