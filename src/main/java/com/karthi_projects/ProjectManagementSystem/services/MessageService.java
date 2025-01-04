package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.Message;

import java.util.List;

public interface MessageService{
    Message sendMessage(Long senderId, Long projectId,String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
