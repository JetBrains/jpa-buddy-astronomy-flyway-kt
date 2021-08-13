package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import com.haulmont.astronomy.embeddable.Dimensions
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "waybill_item")
@Entity
class WaybillItem : BaseEntity() {
    @Column(name = "number")
    var number: Int? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "weight")
    var weight: Double? = null

    @Embedded
    var dimensions: Dimensions? = null

    @Column(name = "charge", precision = 19, scale = 2)
    var charge: BigDecimal? = null
}