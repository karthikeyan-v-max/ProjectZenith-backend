package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.Model.Chat;
import com.karthi_projects.ProjectManagementSystem.Model.Invitation;
import com.karthi_projects.ProjectManagementSystem.Model.Project;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.services.InvitationService;
import com.karthi_projects.ProjectManagementSystem.services.InviteRequest;
import com.karthi_projects.ProjectManagementSystem.services.ProjectService;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController { 
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category ,
            @RequestParam(required = false) String tag ,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user , category , tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) throws Exception {
        Project project = projectService.getProjectbyId(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Project createdProject = projectService.createProject(project,user);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
                                                 @RequestBody Project project,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        Project updatedProject = projectService.updateProject(project , projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId , user.getId());
        return new ResponseEntity<>("The project is deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(@RequestParam(required = false) String keyword,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectService.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.FOUND);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(@PathVariable Long projectId , @RequestHeader("Authorization") String jwt) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<String> inviteProject(
            @RequestBody InviteRequest req,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());

        return new ResponseEntity<>("User invitation sent successfully", HttpStatus.OK);
    }

    @PostMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInvitation(
            @RequestParam String token,
            @RequestBody Project project,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId() , user.getId());

        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }
}
