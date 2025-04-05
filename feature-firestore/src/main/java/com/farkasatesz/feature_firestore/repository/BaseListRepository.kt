package com.farkasatesz.feature_firestore.repository

import com.farkasatesz.core.model.BaseList
import com.google.firebase.firestore.QuerySnapshot

interface BaseListRepository : Repository<BaseList>{
    suspend fun getListByQuery(query: String): QuerySnapshot
}