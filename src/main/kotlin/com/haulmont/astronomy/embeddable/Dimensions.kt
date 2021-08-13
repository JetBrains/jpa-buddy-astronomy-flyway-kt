package com.haulmont.astronomy.embeddable

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Dimensions {
    @Column(name = "length")
    var length: Double? = null

    @Column(name = "width")
    var width: Double? = null

    @Column(name = "height")
    var height: Double? = null
}