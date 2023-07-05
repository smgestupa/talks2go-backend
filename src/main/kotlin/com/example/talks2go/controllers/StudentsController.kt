package com.example.talks2go.controllers

import com.example.talks2go.models.Student
import com.example.talks2go.payloads.requests.LoginRequest
import com.example.talks2go.payloads.requests.LogoutRequest
import com.example.talks2go.payloads.responses.DataResponse
import com.example.talks2go.payloads.responses.MessageResponse
import com.example.talks2go.repositories.StudentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin(origins=["*"], maxAge=3600)
@RestController
@RequestMapping("/api/v1/students")
class StudentsController constructor(
    val studentRepository: StudentRepository
) {

    @PostMapping(
            value = ["/login"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun loginStudent(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        studentRepository.findById(loginRequest.studentEmail)
            .let {

                if (it.isPresent && it.get().password == loginRequest.password) {
                    val student: Student = it.get();
                    studentRepository.insertStudentAsLoggedIn(student.studentEmail);

                    return ResponseEntity(
                        DataResponse(student, status.value()),
                        status
                    );
                } else if (it.isPresent && it.get().password != loginRequest.password) {
                    status = HttpStatus.UNAUTHORIZED;

                    return ResponseEntity(
                        MessageResponse("Student Email or Password is invalid", status.value()),
                        status
                    );
                }
            };

        val newStudent: Student = studentRepository.save(
            Student(loginRequest.studentEmail, loginRequest.password)
        );

        studentRepository.insertStudentAsLoggedIn(newStudent.studentEmail);

        return ResponseEntity(
            DataResponse(newStudent, status.value()),
            status
        );
    }

    @PostMapping(
            value = ["/logout"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun logoutStudent(@RequestBody logoutRequest: LogoutRequest): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        studentRepository.deleteStudentAsLoggedIn(logoutRequest.studentEmail);

        return ResponseEntity(
            MessageResponse("Student successfully logged out", status.value()),
            status
        );
    }

    @GetMapping(
            value = ["/logged/list"],
            produces = ["application/json"]
    )
    @Throws(Exception::class)
    fun getStudentsLoggedIn(): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        val loggedInStudents: List<String> = studentRepository.selectedLoggedInStudents();

        return ResponseEntity(
            DataResponse(loggedInStudents, status.value()),
            status
        );
    }
}