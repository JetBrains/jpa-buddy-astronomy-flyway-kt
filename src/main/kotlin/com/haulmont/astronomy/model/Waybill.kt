package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "waybill")
@Entity
class Waybill : BaseEntity() {
    @Column(name = "reference")
    var reference: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id")
    var shipper: Customer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consignee_id")
    var consignee: Customer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_port_id")
    var departurePort: Spaceport? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_port_id")
    var destinationPort: Spaceport? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    var carrier: Carrier? = null

    @OneToMany(mappedBy = "waybill")
    var items: MutableList<WaybillItem> = mutableListOf()

    @Column(name = "total_weight")
    var totalWeight: Double? = null

    @Column(name = "total_charge", precision = 19, scale = 2)
    var totalCharge: BigDecimal? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Waybill

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1707949113
}