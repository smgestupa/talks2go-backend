package com.example.talks2go.models

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class Message constructor(
        @Id
        @Column(name="message_id")
        @GeneratedValue(strategy= GenerationType.AUTO)
        val messageID: Int = 0,

        @Column(
                name="room_id",
                nullable=false
        )
        val roomID: Int = 0,

        @Column(
                name="student_email",
                length=32,
                nullable=false
        )
        val studentEmail: String,

        @Column(
                length=1024,
                nullable=false
        )
        val content: String,

        @Column(
                name="created_at",
                nullable=false
        )
        @CreationTimestamp
        val createdAt: LocalDateTime,
)