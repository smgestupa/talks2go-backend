package com.example.talks2go.payloads.requests

data class LoginRequest constructor(
        val studentEmail: String,
        val password: String
)