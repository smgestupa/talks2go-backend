package com.example.talks2go.models

import org.hibernate.annotations.CreationTimestamp
import javax.persistence.*

@Entity
@Table
data class Student constructor(
        @Id
        @Column(
                name="student_email",
                length=32
        )
        val studentEmail: String,

        @Column(
                length=32,
                nullable=false
        )
        val password: String
)