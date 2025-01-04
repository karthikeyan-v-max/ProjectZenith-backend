package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.Model.Chat;
import com.karthi_projects.ProjectManagementSystem.Model.Message;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.request.MessageRequest;
import com.karthi_projects.ProjectManagementSystem.services.MessageService;
import com.karthi_projects.ProjectManagementSystem.services.ProjectService;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest requset) throws Exception {
        User user = userService.findUserById(requset.getSenderID());

        if(user == null) {throw new Exception("User not found");}
        Chat chats = projectService.getProjectbyId(requset.getProjectID()).getChat();
        if(chats == null) {throw new Exception("Chat not found");}

        Message sentMessage = messageService.sendMessage(requset.getSenderID(), requset.getProjectID(), requset.getContent());

        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")

    public ResponseEntity<List<Message>> getMessages(@PathVariable("projectId") Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
