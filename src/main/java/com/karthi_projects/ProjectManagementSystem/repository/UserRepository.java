package com.karthi_projects.ProjectManagementSystem.repository;

import com.karthi_projects.ProjectManagementSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
    User findByEmail(String email);
}
