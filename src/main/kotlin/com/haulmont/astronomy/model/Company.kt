package com.haulmont.astronomy.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@DiscriminatorValue("1")
@Table(name = "company")
@Entity
class Company : Customer() {
    @Column(name = "registration_id")
    var registrationId: String? = null

    @Column(name = "company_type")
    var companyType: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Company

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 56842787
}