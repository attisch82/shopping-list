package com.farkasatesz.core.model

data class UnitType(
    var id: String = "",
    var name: String = ""
): FirestoreModel<UnitType> {
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name
        )
    }
    override fun fromMap(map: Map<String, Any>): UnitType {
        return UnitType(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: ""
        )
    }
}
