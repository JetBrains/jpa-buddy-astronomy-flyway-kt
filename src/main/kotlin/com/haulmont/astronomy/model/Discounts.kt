package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "discounts")
@Entity
class Discounts : BaseEntity() {
    @Column(name = "value", precision = 19, scale = 2)
    var value: BigDecimal? = null

    @Enumerated
    @Column(name = "grade")
    var grade: CustomerGrade? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Discounts

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1236869400
}