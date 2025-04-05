package com.farkasatesz.feature_firestore.repositoryImpl

import com.farkasatesz.core.model.UnitType
import com.farkasatesz.feature_firestore.contracts.CustomQuery
import com.farkasatesz.feature_firestore.repository.Repository
import com.farkasatesz.feature_firestore.repository.UnitTypeRepository
import com.farkasatesz.feature_firestore.util.firestoreOperation.FirestoreOperation
import com.google.firebase.firestore.FirebaseFirestore

class UnitTypeRepositoryImpl(
    firestore: FirebaseFirestore
) : UnitTypeRepository,
    Repository<UnitType> by FirestoreRepositoryImpl(firestore, "unitTypes")
{
    private val collection = firestore.collection("unitTypes")
    private val customQuery = CustomQuery<UnitType> { query ->
        FirestoreOperation.getByQuery(collection, "name", query)
    }

    override suspend fun getUnitTypeByQuery(query: String) = customQuery.query(query)

}