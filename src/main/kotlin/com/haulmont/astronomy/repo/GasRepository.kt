package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Gas
import org.springframework.data.jpa.repository.JpaRepository

interface GasRepository : JpaRepository<Gas, Long> {
    fun findByNameIs(name: String): Gas?
}