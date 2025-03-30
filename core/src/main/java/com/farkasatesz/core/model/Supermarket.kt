package com.farkasatesz.core.model

data class Supermarket(
    var id: String = "",
    var name: String = "",
    var locationLat : Double = 0.0,
    var locationLng : Double = 0.0
) : FirestoreModel<Supermarket>{
    override fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "locationLat" to locationLat,
            "locationLng" to locationLng
        )
    }

    override fun fromMap(map: Map<String, Any>): Supermarket {
        return Supermarket(
            id = map["id"] as? String ?: "",
            name = map["name"] as? String ?: "",
            locationLat = map["locationLat"] as? Double ?: 0.0,
            locationLng = map["locationLng"] as? Double ?: 0.0
        )
    }

}
