package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.Chat;
import com.karthi_projects.ProjectManagementSystem.Model.Message;
import com.karthi_projects.ProjectManagementSystem.Model.Project;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.repository.MessageRepository;
import com.karthi_projects.ProjectManagementSystem.repository.ProjectRepository;
import com.karthi_projects.ProjectManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

   @Autowired
   private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userService.findUserById(senderId);

        Chat chat = projectService.getChatByProjectId(projectId);

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }
}
