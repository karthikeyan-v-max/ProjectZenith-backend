package com.karthi_projects.ProjectManagementSystem.repository;

import com.karthi_projects.ProjectManagementSystem.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat , Long> {
}
