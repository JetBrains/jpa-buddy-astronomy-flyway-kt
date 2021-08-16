package com.haulmont.astronomy.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Coordinates {
    @Column(name = "latitude")
    var latitude: Double? = null

    @Column(name = "longitude")
    var longitude: Double? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Coordinates

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        return true
    }

    override fun hashCode(): Int {
        var result: Int = latitude?.hashCode() ?: 0
        result = 31 * result + (longitude?.hashCode() ?: 0)
        return result
    }
}