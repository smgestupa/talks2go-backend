package com.example.talks2go.controllers

import com.example.talks2go.models.Chatroom
import com.example.talks2go.models.Message
import com.example.talks2go.models.Student
import com.example.talks2go.payloads.requests.ChatroomRequest
import com.example.talks2go.payloads.requests.LoginRequest
import com.example.talks2go.payloads.requests.MessageRequest
import com.example.talks2go.payloads.responses.DataResponse
import com.example.talks2go.payloads.responses.MessageResponse
import com.example.talks2go.repositories.ChatroomRepository
import com.example.talks2go.repositories.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin(origins=["*"], maxAge=3600)
@RestController
@RequestMapping("/api/v1/chats")
class ChatsController constructor(
    val messageRepository: MessageRepository,
    val chatroomRepository: ChatroomRepository
) {
    @PostMapping(
        value = ["/chatroom"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun getChatroomID(@RequestBody chatroomRequest: ChatroomRequest): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        var chatroomID: Int? = null;
        val chatroomIDThread = Thread {
            val chatroom: Optional<Int> = chatroomRepository.getChatroomID(
                chatroomRequest.firstStudentEmail,
                chatroomRequest.secondStudentEmail);

            if (chatroom.isPresent)
                chatroomID = chatroom.get();
        };

        chatroomIDThread.start();
        chatroomIDThread.join();

        if (chatroomID == null) {
            val chatroom: Chatroom = Chatroom(
                firstStudentEmail = chatroomRequest.firstStudentEmail,
                secondStudentEmail = chatroomRequest.secondStudentEmail
            );

            chatroomRepository.save(chatroom);

            return ResponseEntity(
                DataResponse(chatroom, status.value()),
                status
            );
        }

        return ResponseEntity(
            DataResponse(chatroomRepository.findById(chatroomID!!).get(), status.value()),
            status
        );
    }
    @PostMapping(
        value = ["/chatroom/list"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun listChatroomMessages(@RequestBody chatroomID: Int): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        var chatroomMessages: List<Message>? = null;
        val chatroomMessagesThread = Thread {
            chatroomMessages = messageRepository.getChatroomMessages(chatroomID);
        };

        chatroomMessagesThread.start();
        chatroomMessagesThread.join();

        if (chatroomMessages == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;

            return ResponseEntity(
                MessageResponse("Something went wrong with retrieving chat messages", status.value()),
                status
            );
        }

        return ResponseEntity(
            DataResponse(chatroomMessages!!, status.value()),
            status
        );
    }
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
    @PostMapping(
        value = ["/message/history"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    @ResponseBody
    @Throws(Exception::class)
    fun listStudentMessageHistory(@RequestBody studentEmail: String): ResponseEntity<Any> {
        var status: HttpStatus = HttpStatus.OK;

        var messageHistory: List<Message>? = null;
        val messageHistoryThread = Thread {
            messageHistory = messageRepository.getStudentHistoryMessages(studentEmail);
        }

        messageHistoryThread.start();
        messageHistoryThread.join();

        if (messageHistory == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;

            return ResponseEntity(
                MessageResponse("Something went wrong with retrieving chat history messages", status.value()),
                status
            );
        }

        return ResponseEntity(
            DataResponse(messageHistory!!, status.value()),
            status
        );
    }
}