package com.farkasatesz.core.model

data class BaseList(
    var id: String = "",
    var name: String = ""
) : FirestoreModel<BaseList> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name
        )
    }
    override fun fromMap(map: Map<String, Any>): BaseList {
        return BaseList(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: ""
        )
    }
}
