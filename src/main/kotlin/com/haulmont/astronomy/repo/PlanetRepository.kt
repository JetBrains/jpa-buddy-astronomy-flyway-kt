package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.AtmosphericGas
import com.haulmont.astronomy.model.Planet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlanetRepository : JpaRepository<Planet, Long> {

    @Query("select p.atmosphere.gases from Planet p where p.name = ?1")
    fun getAtmosphericGasesByName(name: String): List<AtmosphericGas>


    fun findByName(name: String): Planet?

}