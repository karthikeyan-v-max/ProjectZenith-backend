package com.karthi_projects.ProjectManagementSystem.repository;

import com.karthi_projects.ProjectManagementSystem.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long id);
}
