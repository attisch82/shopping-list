package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.Item
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.contracts.Filter
import com.farkasatesz.feature_firestore.repository.ItemRepository
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class ItemRepositoryImpl(
    firestore: FirebaseFirestore
) : ItemRepository,
    Repository<Item> by FirestoreRepositoryImpl(firestore, "items")
{
    private val collection = firestore.collection("items")
    private val customQuery = CustomQuery<Item> { query ->
        FirestoreOperation.getByQuery(collection, "name", query)
    }
    private val filter = Filter<Item> { fieldName, value ->
        FirestoreOperation.getListByFieldName(collection, fieldName, value)
    }

    override suspend fun getItemsByQuery(query: String) = customQuery.query(query)

    override suspend fun getItemsByCategory(categoryName: String) = filter.by("categoryName", categoryName)

    override suspend fun getItemsByBrand(brandName: String) = filter.by("brand", brandName)
}