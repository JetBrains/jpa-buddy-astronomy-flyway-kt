package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Atmosphere
import com.haulmont.astronomy.model.AtmosphericGas
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AtmosphereRepository : JpaRepository<Atmosphere, Long> {
    @Query("select a.gases from Atmosphere a where a = ?1")
    fun findAllGasesInAtmosphere(atmosphere: Atmosphere): List<AtmosphericGas>
}