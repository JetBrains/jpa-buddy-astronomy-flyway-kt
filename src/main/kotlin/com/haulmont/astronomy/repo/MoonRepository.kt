package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Moon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface MoonRepository : JpaRepository<Moon, Long> {

    fun findByName(name: String): Moon?

    @Query("select m from Moon m where m.planet.rings = ?1")
    @Transactional(readOnly = true)
    fun findByPlanet_RingsEquals(rings: Boolean): List<Moon>

}