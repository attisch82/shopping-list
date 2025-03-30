package com.farkasatesz.core.model

data class PriceTracker(
    var id: String = "",
    var shoppingItemId: String = "",
    var savedAt: Long = System.currentTimeMillis()
) : FirestoreModel<PriceTracker> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "shoppingItemId" to shoppingItemId,
            "savedAt" to savedAt
        )
    }
    override fun fromMap(map: Map<String, Any>): PriceTracker {
        return PriceTracker(
            id = map["id"] as? String ?: "",
            shoppingItemId = map["shoppingItemId"] as? String ?: "",
            savedAt = map["savedAt"] as? Long ?: System.currentTimeMillis()
        )
    }
}
