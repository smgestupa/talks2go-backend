package com.example.talks2go.payloads.requests

data class ChatroomRequest constructor(
    val roomID: Int,
    val firstStudentEmail: String,
    val secondStudentEmail: String
)
