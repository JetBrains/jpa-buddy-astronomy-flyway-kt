package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.WaybillItem
import org.springframework.data.jpa.repository.JpaRepository

interface WaybillItemRepository : JpaRepository<WaybillItem, Long> {

    fun existsByNumber(number: Int): Boolean


    fun findByNameOrderByWeightAsc(name: String): List<WaybillItem>

}