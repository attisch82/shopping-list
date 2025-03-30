package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.BaseList
import kotlinx.coroutines.flow.Flow

interface BaseListRepository : Repository<BaseList>{
    fun getListByQuery(query: String): Flow<List<BaseList>>
}