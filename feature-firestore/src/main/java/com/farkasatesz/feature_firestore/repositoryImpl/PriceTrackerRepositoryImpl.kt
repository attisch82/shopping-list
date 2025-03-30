package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.PriceTracker
import com.farkasatesz.feature_firestore.repository.PriceTrackerRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class PriceTrackerRepositoryImpl(
    firestore: FirebaseFirestore
) : PriceTrackerRepository {
    val collection = firestore.collection("priceTrackers")
    override fun getPricesByShoppingItem(shoppingItemId: String) =
        FirestoreOperation.getListByFieldName<PriceTracker>(
            collection,
            "shoppingItemId",
            shoppingItemId
        )

    override suspend fun getLastPrice(shoppingItemId: String): Double {
        val snapshot = collection
            .whereEqualTo("shoppingItemId", shoppingItemId)
            .orderBy("date", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
        return snapshot.documents.firstOrNull()?.getDouble("price") ?: 0.0
    }

    override fun getAll() =
        FirestoreOperation.getAll<PriceTracker>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<PriceTracker>(collection, id, PriceTracker("0", "error"))

    override suspend fun add(item: PriceTracker) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: PriceTracker) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: PriceTracker) =
        FirestoreOperation.delete(collection, item.id)
}