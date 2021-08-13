package com.haulmont.astronomy.embeddable

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Coordinates {
    @Column(name = "latitude")
    var latitude: Double? = null

    @Column(name = "longitude")
    var longitude: Double? = null
}