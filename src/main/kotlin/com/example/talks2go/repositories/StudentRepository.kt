package com.example.talks2go.repositories

import com.example.talks2go.models.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, String> {
}