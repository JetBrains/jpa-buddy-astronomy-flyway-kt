package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import com.haulmont.astronomy.basemodel.Customer
import java.math.BigDecimal
import javax.persistence.*

@Table(name = "waybill")
@Entity
class Waybill : BaseEntity() {
    @Column(name = "reference")
    var reference: String? = null

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    var shipper: Customer? = null

    @ManyToOne
    @JoinColumn(name = "consignee_id")
    var consignee: Customer? = null

    @ManyToOne
    @JoinColumn(name = "departure_port_id")
    var departurePort: Spaceport? = null

    @ManyToOne
    @JoinColumn(name = "destination_port_id")
    var destinationPort: Spaceport? = null

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    var carrier: Carrier? = null

    @OneToMany
    @JoinColumn(name = "waybill_id")
    var items: MutableList<WaybillItem>? = mutableListOf()

    @Column(name = "total_weight")
    var totalWeight: Double? = null

    @Column(name = "total_charge", precision = 19, scale = 2)
    var totalCharge: BigDecimal? = null
}