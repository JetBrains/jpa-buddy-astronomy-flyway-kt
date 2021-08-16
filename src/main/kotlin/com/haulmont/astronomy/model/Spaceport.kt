package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
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

    @ManyToMany(mappedBy = "spaceports", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var carriers: MutableList<Carrier> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Spaceport

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1790982374
}