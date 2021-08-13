package com.haulmont.astronomy.model

import com.haulmont.astronomy.basemodel.Customer
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "individual")
@Entity
class Individual : Customer() {
    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null
}