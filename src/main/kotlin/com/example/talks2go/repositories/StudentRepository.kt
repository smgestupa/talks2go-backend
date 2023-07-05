package com.example.talks2go.repositories

import com.example.talks2go.models.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface StudentRepository : JpaRepository<Student, String> {

    @Transactional
    @Modifying
    @Query(
        value = "INSERT IGNORE logged_user VALUES (:studentEmail)",
        nativeQuery = true
    )
    fun insertStudentAsLoggedIn(@Param("studentEmail") studentEmail: String);

    @Transactional
    @Modifying
    @Query(
        value = "DELETE FROM logged_user WHERE student_email = :studentEmail",
        nativeQuery = true
    )
    fun deleteStudentAsLoggedIn(@Param("studentEmail") studentEmail: String);

    @Query(
        value = "SELECT student_email FROM logged_user",
        nativeQuery = true
    )
    fun selectedLoggedInStudents(): List<String>;
}