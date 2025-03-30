package com.farkasatesz.core.model

interface FirestoreModel <T> {
    fun toMap(): Map<String, Any>
    fun fromMap(map: Map<String, Any>): T
}