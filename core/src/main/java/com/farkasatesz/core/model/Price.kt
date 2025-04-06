package com.farkasatesz.core.model

data class Price(
    var id: String = "",
    var price: Double = 0.0,
    var currency: String = ""
): FirestoreModel<Price> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "price" to price,
            "currency" to currency
        )
    }

    override fun fromMap(map: Map<String, Any>): Price {
        return Price(
            id = map["id"] as? String ?: "",
            price = map["price"] as? Double ?: 0.0,
            currency = map["currency"] as? String ?: ""
        )
    }
}
