package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.BaseEntity
import com.haulmont.astronomy.embeddable.Coordinates
import javax.persistence.*

@Table(name = "spaceport")
@Entity
class Spaceport : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @ManyToOne
    @JoinColumn(name = "planet_id")
    var planet: Planet? = null

    @ManyToOne
    @JoinColumn(name = "moon_id")
    var moon: Moon? = null

    @Column(name = "is_default")
    var isDefault: Boolean? = null

    @Embedded
    var coordinates: Coordinates? = null

    @ManyToMany(mappedBy = "spaceports", cascade = [CascadeType.ALL])
    var carriers: MutableList<Carrier>? = mutableListOf()
}