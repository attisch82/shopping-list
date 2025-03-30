package com.farkasatesz.core.model

data class ShoppingList(
    var id: String = "",
    var baseListId: String = "",
    var supermarketId: String = ""
) : FirestoreModel<ShoppingList> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "baseListId" to baseListId,
            "supermarketId" to supermarketId
        )
    }
    override fun fromMap(map: Map<String, Any>): ShoppingList {
        return ShoppingList(
            id = map["id"] as? String ?: "",
            baseListId = map["baseListId"] as? String ?: "",
            supermarketId = map["supermarketId"] as? String ?: ""
        )
    }
}
