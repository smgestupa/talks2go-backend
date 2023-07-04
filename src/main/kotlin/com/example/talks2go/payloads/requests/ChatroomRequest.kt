package com.example.talks2go.payloads.requests

data class ChatroomRequest constructor(
    val firstStudentEmail: String,
    val secondStudentEmail: String
)
