package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import javax.persistence.*

@Table(name = "atmosphere")
@Entity
class Atmosphere : BaseEntity() {
    @Column(name = "description")
    var description: String? = null

    @Column(name = "pressure")
    var pressure: Double? = null

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "atmosphere_id")
    var gases: MutableList<AtmosphericGas> = mutableListOf()
}