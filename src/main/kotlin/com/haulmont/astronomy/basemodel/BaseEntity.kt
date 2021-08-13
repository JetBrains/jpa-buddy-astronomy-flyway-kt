package com.haulmont.astronomy.basemodel

import java.io.Serializable
import javax.persistence.*
import kotlin.reflect.KClass

@MappedSuperclass
class BaseEntity : Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isNew(): Boolean {
        return id == null
    }
}

fun KClass<out BaseEntity>.tableName(): String? {
    return this.annotations.filterIsInstance<Table>().firstOrNull()?.name
}
