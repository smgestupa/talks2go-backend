package com.example.talks2go.repositories

import com.example.talks2go.models.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Int> {
}