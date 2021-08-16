package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.BaseEntity
import org.hibernate.Hibernate
import javax.persistence.*

@Table(name = "atmosphere")
@Entity
class Atmosphere : BaseEntity() {
    @Column(name = "description")
    var description: String? = null

    @Column(name = "pressure")
    var pressure: Double? = null

    @OneToMany(mappedBy = "atmosphere", orphanRemoval = true)
    var gases: MutableList<AtmosphericGas> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Atmosphere

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1346029679
}