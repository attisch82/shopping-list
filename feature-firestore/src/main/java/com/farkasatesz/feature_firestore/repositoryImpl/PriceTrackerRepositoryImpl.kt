package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.PriceTracker
import com.farkasatesz.feature_firestore.contracts.Filter
import com.farkasatesz.feature_firestore.contracts.FindOne
import com.farkasatesz.feature_firestore.repository.PriceTrackerRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class PriceTrackerRepositoryImpl(
    firestore: FirebaseFirestore
) : PriceTrackerRepository,
    Repository<PriceTracker> by FirestoreRepositoryImpl(firestore, "priceTrackers")
{
    private val collection = firestore.collection("priceTrackers")
    private val filter = Filter<PriceTracker> { fieldName, value ->
        FirestoreOperation.getListByFieldName(collection, fieldName, value)
    }
    private val findOne = FindOne<PriceTracker> { id ->
        FirestoreOperation.getById(collection, id)
    }

    override suspend fun getPricesByShoppingItem(shoppingItemId: String) = filter.by("shoppingItemId", shoppingItemId)

    override suspend fun getLastPrice(shoppingItemId: String) = findOne.by(shoppingItemId)
}