package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Waybill
import org.springframework.data.jpa.repository.JpaRepository

interface WaybillRepository : JpaRepository<Waybill, Long> {

    fun findByReference(reference: String): Waybill?


    fun findByTotalWeightGreaterThan(totalWeight: Double): List<Waybill>

}