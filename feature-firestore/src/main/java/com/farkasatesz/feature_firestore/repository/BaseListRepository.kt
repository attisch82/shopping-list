package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.BaseList

interface BaseListRepository : Repository<BaseList>{
    suspend fun getListByQuery(query: String): List<BaseList>
}