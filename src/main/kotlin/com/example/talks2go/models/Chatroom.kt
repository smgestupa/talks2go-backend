package com.example.talks2go.models

import javax.persistence.*

@Entity
@Table
data class Chatroom constructor(
        @Id
        @Column(name="room_id")
        @GeneratedValue(strategy= GenerationType.AUTO)
        val roomID: Int = 0,

        @Column(
                name="first_student_email",
                length=32,
                nullable=false
        )
        val firstStudentEmail: String,

        @Column(
                name="second_student_email",
                length=32,
                nullable=false
        )
        val secondStudentEmail: String
)
