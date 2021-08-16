package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import javax.persistence.*

@Table(name = "atmospheric_gas")
@Entity
class AtmosphericGas : BaseEntity() {
    @Column(name = "volume")
    var volume: Double? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gas_id")
    var gas: Gas? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atmosphere_id")
    var atmosphere: Atmosphere? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AtmosphericGas

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1544558389
}