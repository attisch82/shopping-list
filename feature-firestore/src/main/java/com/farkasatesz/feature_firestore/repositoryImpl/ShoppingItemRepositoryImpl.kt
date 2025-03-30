package com.farkasatesz.feature_firestore.repositoryImpl

import android.util.Log
import com.farkasatesz.core.model.ShoppingItem
import com.farkasatesz.feature_firestore.repository.ShoppingItemRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ShoppingItemRepositoryImpl(
    firestore: FirebaseFirestore
) : ShoppingItemRepository {
    val collection = firestore.collection("shoppingItems")

    override fun getItemsWithQuantityMapped(shoppingListId: String): Flow<Map<String, Int>> {
        val items = FirestoreOperation.getListByFieldName<ShoppingItem>(
            collection,
            "shoppingListId",
            shoppingListId
        )
        return flow {
            items.collect {
                val map = convertMap(it.groupBy { item -> item.name })
                emit(map)
            }
        }.catch {
            emit(emptyMap())
            Log.e("ShoppingItemRepository", "getItemsWithQuantityMapped: ${it.message}", it )
        }
    }

    private fun <T> convertMap(map: Map<String, List<T>>) : Map<String, Int> {
        val result = mutableMapOf<String, Int>()
        for ((key, value) in map) {
            result[key] = value.size
        }
        return result
    }

    override fun getAll() =
        FirestoreOperation.getAll<ShoppingItem>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<ShoppingItem>(collection, id, ShoppingItem("0", "error"))

    override suspend fun add(item: ShoppingItem) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: ShoppingItem) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: ShoppingItem) =
        FirestoreOperation.delete(collection, item.id)

}