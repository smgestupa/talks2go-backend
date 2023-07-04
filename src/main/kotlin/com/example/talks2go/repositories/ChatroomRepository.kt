package com.example.talks2go.repositories

import com.example.talks2go.models.Chatroom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatroomRepository : JpaRepository<Chatroom, Int> {
}