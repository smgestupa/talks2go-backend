package com.example.talks2go.controllers

import com.example.talks2go.payloads.responses.MessageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins=["*"], maxAge=3600)
@RestController
@RequestMapping("/api/v1/chats")
class ChatsController {
//
//    @PostMapping(
//            value = ["/get"],
//            consumes = ["application/json"],
//            produces = ["application/json"]
//    )
//    @ResponseBody
//    @Throws(Exception::class)
//    suspend fun retrieveBookMessages(@RequestBody ): ResponseEntity<Any> {
//        var status: HttpStatus = HttpStatus.NOT_FOUND;
//
//        return ResponseEntity(
//                MessageResponse("Something went wrong with retrieving chat messages", status.value()),
//                status
//        );
//    }
}