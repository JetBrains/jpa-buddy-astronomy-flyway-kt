package com.haulmont.astronomy.model

import com.haulmont.astronomy.model.basemodel.AstronomicalBody
import org.hibernate.Hibernate
import javax.persistence.*

@Table(name = "moon")
@Entity
class Moon : AstronomicalBody() {
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "atmosphere_id")
    var atmosphere: Atmosphere? = null

    @ManyToOne
    @JoinColumn(name = "planet_id")
    var planet: Planet? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Moon

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 950581455
}