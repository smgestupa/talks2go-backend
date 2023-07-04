package com.example.talks2go.controllers

import com.example.talks2go.models.Message
import com.example.talks2go.models.Student
import com.example.talks2go.payloads.requests.LoginRequest
import com.example.talks2go.payloads.requests.MessageRequest
import com.example.talks2go.payloads.responses.DataResponse
import com.example.talks2go.payloads.responses.MessageResponse
import com.example.talks2go.repositories.ChatroomRepository
import com.example.talks2go.repositories.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins=["*"], maxAge=3600)
@RestController
@RequestMapping("/api/v1/chats")
class ChatsController constructor(
    val messageRepository: MessageRepository,
    val chatroomRepository: ChatroomRepository
) {
    @PostMapping(
        value = ["/message/send"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun sendMessageToChatroom(@RequestBody messageRequest: MessageRequest): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        val message: Message = Message(
            roomID = messageRequest.roomID,
            studentEmail = messageRequest.studentEmail,
            content = messageRequest.content
        );

        messageRepository.save(message);

        return ResponseEntity(
            MessageResponse("Message has been saved to the database", status.value()),
            status
        );
    }
}