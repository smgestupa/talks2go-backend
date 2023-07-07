package com.example.talks2go.repositories

import com.example.talks2go.models.Chatroom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ChatroomRepository : JpaRepository<Chatroom, Int> {

    @Query(
        value = "SELECT room_id FROM chatroom WHERE first_student_email IN (:firstStudentEmail, :secondStudentEmail) AND second_student_email IN (:firstStudentEmail, :secondStudentEmail)",
        nativeQuery = true
    )
    fun getChatroomID(
        @Param("firstStudentEmail") firstStudentEmail: String,
        @Param("secondStudentEmail") secondStudentEmail: String
    ): Optional<Int>
}