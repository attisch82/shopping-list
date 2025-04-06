package com.farkasatesz.core.model

data class PriceTracker(
    var id: String = "",
    var shoppingItemId: String = "",
    var priceId: String = "",
    var savedAt: Long = System.currentTimeMillis()
) : FirestoreModel<PriceTracker> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "shoppingItemId" to shoppingItemId,
            "priceId" to priceId,
            "savedAt" to savedAt
        )
    }
    override fun fromMap(map: Map<String, Any>): PriceTracker {
        return PriceTracker(
            id = map["id"] as? String ?: "",
            shoppingItemId = map["shoppingItemId"] as? String ?: "",
            priceId = map["priceId"] as? String ?: "",
            savedAt = map["savedAt"] as? Long ?: System.currentTimeMillis()
        )
    }
}
