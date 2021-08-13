package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.Customer
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "company")
@Entity
class Company : Customer() {
    @Column(name = "registration_id")
    var registrationId: String? = null

    @Column(name = "company_type")
    var companyType: String? = null
}