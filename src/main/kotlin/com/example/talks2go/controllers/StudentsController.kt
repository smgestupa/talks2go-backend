package com.example.talks2go.controllers

import com.example.talks2go.payloads.requests.LoginRequest
import com.example.talks2go.payloads.responses.MessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins=["*"], maxAge=3600)
@RestController
@RequestMapping("/api/v1/students")
class StudentsController {

    @PostMapping(
            value = ["/login"],
            consumes = ["application/json"],
            produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun loginStudent(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity(
            MessageResponse("Something went wrong with retrieving chat messages", status.value()),
            status
        );
    }
}