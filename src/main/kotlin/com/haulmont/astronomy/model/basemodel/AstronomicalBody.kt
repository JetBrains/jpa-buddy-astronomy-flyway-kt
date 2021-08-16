package com.haulmont.astronomy.model.basemodel

import java.io.FileDescriptor
import javax.persistence.*

@MappedSuperclass
class AstronomicalBody : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @Column(name = "mass")
    var mass: Double? = null

    @Transient
    var picture: FileDescriptor? = null
}