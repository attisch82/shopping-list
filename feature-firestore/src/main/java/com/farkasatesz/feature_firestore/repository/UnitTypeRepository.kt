package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.UnitType
import kotlinx.coroutines.flow.Flow

interface UnitTypeRepository : Repository<UnitType> {
    fun getUnitTypeByQuery(query: String): Flow<List<UnitType>>
}