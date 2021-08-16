package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import javax.persistence.*

@Table(name = "carrier")
@Entity
class Carrier : BaseEntity() {
    @Column(name = "name")
    var name: String? = null

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "carrier_spaceport",
        joinColumns = [JoinColumn(name = "carrier_id")],
        inverseJoinColumns = [JoinColumn(name = "spaceport_id")]
    )
    var spaceports: MutableSet<Spaceport> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Carrier

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1745767009
}