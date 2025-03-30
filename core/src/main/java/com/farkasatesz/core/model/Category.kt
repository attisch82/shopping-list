package com.farkasatesz.core.model

data class Category(
    var id: String = "",
    var name: String = ""
) : FirestoreModel<Category> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name
        )
    }
    override fun fromMap(map: Map<String, Any>): Category {
        return Category(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: ""
        )
    }
}
