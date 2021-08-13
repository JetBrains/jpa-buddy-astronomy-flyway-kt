package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import javax.persistence.*

@Table(name = "atmospheric_gas")
@Entity
class AtmosphericGas : BaseEntity() {
    @Column(name = "volume")
    var volume: Double? = null

    @ManyToOne
    @JoinColumn(name = "gas_id")
    var gas: Gas? = null
}