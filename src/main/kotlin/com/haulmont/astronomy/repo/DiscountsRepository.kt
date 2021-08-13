package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Discounts
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface DiscountsRepository : JpaRepository<Discounts, Long> {

    fun existsByValueBetween(valueStart: BigDecimal, valueEnd: BigDecimal): Boolean

}