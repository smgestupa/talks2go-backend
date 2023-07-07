package com.example.talks2go.models

import javax.persistence.*

@Entity
@Table(name = "message")
data class MessageHistory constructor(
    @Id
    @Column(name="message_id")
    val messageID: Int,

    @Column(
        name="room_id",
        nullable=false
    )
    val roomID: Int,

    @Column(
        name="student_email",
        length=32,
        nullable=false
    )
    val studentEmail: String,

    @Column
    val recipient: String,

    @Column(
        length=1024,
        nullable=false
    )
    val content: String,

    @Column(
        name="created_at",
        nullable=false
    )
    val createdAt: String,
)