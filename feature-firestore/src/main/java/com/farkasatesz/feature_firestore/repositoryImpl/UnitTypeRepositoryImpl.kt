package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.UnitType
import com.farkasatesz.feature_firestore.repository.UnitTypeRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class UnitTypeRepositoryImpl(
    firestore: FirebaseFirestore
) : UnitTypeRepository {
    val collection = firestore.collection("unitTypes")
    override fun getUnitTypeByQuery(query: String) =
        FirestoreOperation.getByQuery<UnitType>(collection, "name", query)

    override fun getAll() =
        FirestoreOperation.getAll<UnitType>(collection)

    override fun getById(id: String) =
        FirestoreOperation.getById<UnitType>(collection, id, UnitType("0", "error"))

    override suspend fun add(item: UnitType) =
        FirestoreOperation.save(collection, item)

    override suspend fun update(item: UnitType) =
        FirestoreOperation.update(collection, item.id, item)

    override suspend fun delete(item: UnitType) =
        FirestoreOperation.delete(collection, item.id)


}