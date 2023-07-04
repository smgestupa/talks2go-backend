package com.example.talks2go.payloads.requests

data class MessageRequest constructor(
        val roomID: Int,
        val studentEmail: String,
        val content: String
)