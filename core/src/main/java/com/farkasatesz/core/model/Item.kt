package com.farkasatesz.core.model

data class Item(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var brand: String = "",
    var categoryName: String = "",
    var unitTypeName: String = ""
) : FirestoreModel<Item> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "brand" to brand,
            "categoryName" to categoryName,
            "unitTypeName" to unitTypeName
        )
    }

    override fun fromMap(map: Map<String, Any>): Item {
        return Item(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: "",
            description = map["description"] as? String ?: "",
            brand = map["brand"] as? String ?: "",
            categoryName = map["categoryName"] as? String ?: "",
            unitTypeName = map["unitTypeName"] as? String ?: ""
        )
    }

}
