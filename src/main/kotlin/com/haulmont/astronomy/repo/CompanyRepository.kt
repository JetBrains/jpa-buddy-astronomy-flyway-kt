package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Long> {

    fun findByCompanyType(companyType: String): List<Company>

}