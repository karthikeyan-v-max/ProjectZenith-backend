package com.karthi_projects.ProjectManagementSystem.repository;

import com.karthi_projects.ProjectManagementSystem.Model.Project;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {

//    List <Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String name, User user);

   // @Query("SELECT p FROM Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("user") User user);

//    List <Project> findByTeamContainingOwner(User user , User owner);

    List <Project> findProjectByOwner(User owner);
}
