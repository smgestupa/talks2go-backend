package com.example.talks2go.repositories

import com.example.talks2go.models.MessageHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MessageHistoryRepository : JpaRepository<MessageHistory, Int> {

    @Query(
        value = "SELECT m.message_id, m.room_id, m.student_email,\n" +
                "CASE WHEN c.first_student_email = m.student_email THEN c.second_student_email\n" +
                "WHEN c.second_student_email = m.student_email THEN c.first_student_email\n" +
                "END AS 'recipient',\n" +
                "m.content, m.created_at\n" +
                "FROM message m\n" +
                "LEFT JOIN chatroom c\n" +
                "ON (m.student_email = c.first_student_email OR m.student_email = c.second_student_email)\n" +
                "WHERE m.student_email = :studentEmail\n" +
                "ORDER BY m.created_at ASC",
        nativeQuery = true
    )
    fun getStudentHistoryMessages(@Param("studentEmail") studentEmail: String): List<MessageHistory>;
}