package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Carrier
import org.springframework.data.jpa.repository.JpaRepository

interface CarrierRepository : JpaRepository<Carrier, Long> {

    fun findByName(name: String): Carrier?

}