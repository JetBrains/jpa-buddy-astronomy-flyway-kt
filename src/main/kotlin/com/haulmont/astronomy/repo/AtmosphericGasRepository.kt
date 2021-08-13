package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.AtmosphericGas
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AtmosphericGasRepository : JpaRepository<AtmosphericGas, Long> {

    fun countByVolumeEquals(volume: Double?): Long


    fun findByGas_NameLike(name: String?): Optional<AtmosphericGas>

}