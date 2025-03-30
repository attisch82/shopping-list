package com.farkasatesz.core.model

data class ShoppingItem(
    var id: String = "",
    var name: String = "",
    var supermarketName: String = "",
    var shoppingListId: String = "",
    var price: Double = 0.0
) : FirestoreModel<ShoppingItem> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "supermarketName" to supermarketName,
            "shoppingListId" to shoppingListId,
            "price" to price
        )
    }
    override fun fromMap(map: Map<String, Any>): ShoppingItem {
        return ShoppingItem(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: "",
            supermarketName = map["supermarketName"] as? String ?: "",
            shoppingListId = map["shoppingListId"] as? String ?: "",
            price = map["price"] as? Double ?: 0.0
        )
    }
}
