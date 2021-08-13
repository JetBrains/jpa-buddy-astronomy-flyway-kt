package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import com.haulmont.astronomy.enummodel.CustomerGrade
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "discounts")
@Entity
class Discounts : BaseEntity() {
    @Column(name = "value", precision = 19, scale = 2)
    var value: BigDecimal? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    var grade: CustomerGrade? = null
}