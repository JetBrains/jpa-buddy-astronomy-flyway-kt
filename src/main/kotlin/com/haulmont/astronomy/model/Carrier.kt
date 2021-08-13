package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import javax.persistence.*

@Table(name = "carrier")
@Entity
class Carrier : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "CARRIER_SPACEPORT",
        joinColumns = [JoinColumn(name = "CARRIER_id")],
        inverseJoinColumns = [JoinColumn(name = "SPACEPORT_id")]
    )
    var spaceports: MutableList<Spaceport>? = mutableListOf()
}