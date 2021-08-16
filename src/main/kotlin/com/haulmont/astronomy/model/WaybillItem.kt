package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waybill_id")
    var waybill: Waybill? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as WaybillItem

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1839033280
}