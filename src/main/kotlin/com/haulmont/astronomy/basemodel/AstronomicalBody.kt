package com.haulmont.astronomy.basemodel

import java.io.FileDescriptor
import javax.persistence.*

@Table(name = "astronomical_body")
@MappedSuperclass
class AstronomicalBody : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @Column(name = "mass")
    var mass: Double? = null

    @Transient
    var picture: FileDescriptor? = null
}