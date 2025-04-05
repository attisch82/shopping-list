package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.UnitType
import com.google.firebase.firestore.QuerySnapshot

interface UnitTypeRepository : Repository<UnitType> {
   suspend fun getUnitTypeByQuery(query: String): QuerySnapshot
}