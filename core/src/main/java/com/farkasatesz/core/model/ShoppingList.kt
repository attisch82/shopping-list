package com.farkasatesz.core.model

data class ShoppingList(
    var id: String = "",
    var name: String = "",
    var supermarketName: String = ""
) : FirestoreModel<ShoppingList> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "supermarketName" to supermarketName
        )
    }
    override fun fromMap(map: Map<String, Any>): ShoppingList {
        return ShoppingList(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: "",
            supermarketName = map["supermarketName"] as? String ?: ""
        )
    }
}
