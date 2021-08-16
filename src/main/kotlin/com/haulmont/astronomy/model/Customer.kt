package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import javax.persistence.*
import javax.validation.constraints.Email

@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
class Customer : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @Email
    @Column(name = "email")
    var email: String? = null

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "grade")
    var grade: CustomerGrade? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Customer

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 339958611
}