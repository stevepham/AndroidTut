package com.ht117.demo.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class UserEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var old: Int = 0
) : RealmObject()

data class UserModel(val id: String, val name: String, val old: Int)

fun entityToModel(entity: UserEntity): UserModel {
    return UserModel(entity.id, entity.name, entity.old)
}