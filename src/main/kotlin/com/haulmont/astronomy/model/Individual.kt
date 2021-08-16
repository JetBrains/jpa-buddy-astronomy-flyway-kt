package com.haulmont.astronomy.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@DiscriminatorValue("2")
@Table(name = "individual")
@Entity
class Individual : Customer() {
    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Individual

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 26281562
}