package com.example.talks2go.payloads.requests

data class MessageRequest constructor(
        val studentEmail: String,
        val content: String
)