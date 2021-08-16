package com.haulmont.astronomy.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Dimensions {
    @Column(name = "length")
    var length: Double? = null

    @Column(name = "width")
    var width: Double? = null

    @Column(name = "height")
    var height: Double? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Dimensions

        if (length != other.length) return false
        if (width != other.width) return false
        if (height != other.height) return false
        return true
    }

    override fun hashCode(): Int {
        var result: Int = length?.hashCode() ?: 0
        result = 31 * result + (width?.hashCode() ?: 0)
        result = 31 * result + (height?.hashCode() ?: 0)
        return result
    }
}