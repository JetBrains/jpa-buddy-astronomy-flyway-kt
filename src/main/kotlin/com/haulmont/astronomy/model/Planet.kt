package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.AstronomicalBody
import javax.persistence.*

@Table(name = "planet")
@Entity
class Planet : AstronomicalBody() {
    @Column(name = "semi_major_axis")
    var semiMajorAxis: Double? = null

    @Column(name = "orbital_period")
    var orbitalPeriod: Double? = null

    @Column(name = "rotation_period")
    var rotationPeriod: Double? = null

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "atmosphere_id")
    var atmosphere: Atmosphere? = null

    @Column(name = "rings")
    var rings: Boolean? = null
}