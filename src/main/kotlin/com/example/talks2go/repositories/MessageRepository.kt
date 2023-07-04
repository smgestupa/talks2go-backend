package com.example.talks2go.repositories

import com.example.talks2go.models.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MessageRepository : JpaRepository<Message, Int> {

    @Query(
        value = "SELECT * FROM message WHERE room_id = :roomID",
        nativeQuery = true
    )
    fun getChatroomMessages(@Param("roomID") roomID: Int): List<Message>;

    @Query(
        value = "SELECT * FROM message WHERE student_email = :studentEmail",
        nativeQuery = true
    )
    fun getStudentHistoryMessages(@Param("studentEmail") studentEmail: String): List<Message>;
}