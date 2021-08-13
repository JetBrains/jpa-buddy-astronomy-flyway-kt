package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import javax.persistence.*

@Table(name = "gas")
@Entity
class Gas : BaseEntity() {
    @Column(name = "name")
    var name: String? = null
}