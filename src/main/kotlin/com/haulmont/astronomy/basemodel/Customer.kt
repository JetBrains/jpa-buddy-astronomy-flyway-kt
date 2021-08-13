package com.haulmont.astronomy.basemodel

import com.haulmont.astronomy.enummodel.CustomerGrade
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class Customer : BaseEntity(){
    @Column(name = "name")
    var name: String? = null

    @Email
    @Column(name = "email")
    var email: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    var grade: CustomerGrade? = null
}