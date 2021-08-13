package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Spaceport
import org.springframework.data.jpa.repository.JpaRepository

interface SpaceportRepository : JpaRepository<Spaceport, Long> {

    fun findByName(name: String): Spaceport


    fun findByIsDefaultEqualsOrderByNameAsc(isDefault: Boolean): List<Spaceport>

}