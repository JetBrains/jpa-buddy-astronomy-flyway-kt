package com.haulmont.astronomy.repo;

import com.haulmont.astronomy.model.Individual
import org.springframework.data.jpa.repository.JpaRepository

interface IndividualRepository : JpaRepository<Individual, Long> {

    fun findByFirstNameAndEmail(firstName: String, email: String): Individual?

}