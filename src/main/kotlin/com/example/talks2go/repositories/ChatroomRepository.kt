package com.example.talks2go.repositories

import com.example.talks2go.models.Chatroom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ChatroomRepository : JpaRepository<Chatroom, Int> {

    @Query(
        value = "SELECT room_id FROM chatroom WHERE first_student_email = :firstStudentEmail AND second_student_email = :secondStudentEmail",
        nativeQuery = true
    )
    fun getChatroomID(
        @Param("firstStudentEmail") firstStudentEmail: String,
        @Param("secondStudentEmail") secondStudentEmail: String
    ): Int
}