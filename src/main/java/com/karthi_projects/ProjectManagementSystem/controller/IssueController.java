package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.DTO.IssuesDTO;
import com.karthi_projects.ProjectManagementSystem.Model.Issues;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.response.AuthResponse;
import com.karthi_projects.ProjectManagementSystem.response.IssueRequest;
import com.karthi_projects.ProjectManagementSystem.services.IssueService;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;

    // @GetMapping
//     public ResponseEntity<List<Issues>> getAllIssues () throws Exception {
//         List<Issues> issues = issueService.getAllIssues();
//         return ResponseEntity.ok (issues);
//     }
    @GetMapping("/{issueId}")
    public ResponseEntity<Issues> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issues>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssuesDTO> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String token) throws Exception {
        System.out.println("issue--------" + issue);
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

            Issues createdIssue = issueService.createIssue(issue, tokenUser);
            IssuesDTO issueDT0 = new IssuesDTO();
            issueDT0.setDescription(createdIssue.getDescription());
            issueDT0.setDueDate(createdIssue.getDueDate());
            issueDT0.setId(createdIssue.getId());
            issueDT0.setPriority(createdIssue.getPriority());
            issueDT0.setProject(createdIssue.getProject());
            issueDT0.setProjectID(createdIssue.getProjectID());
            issueDT0.setStatus(createdIssue.getStatus());
            issueDT0.setTitle(createdIssue.getTitle());
            issueDT0.setTags(createdIssue.getTags());
            issueDT0.setAssignee(createdIssue.getAssignee());
            return ResponseEntity.ok(issueDT0);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<AuthResponse> deleteIssue (@PathVariable Long issueId,
                                                     @RequestHeader("Authorization") String token)
            throws Exception {
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssues(issueId, user.getId());
        AuthResponse res = new AuthResponse();
        res.setMessage("Issue deleted");
        res.setJwt(token);
        return ResponseEntity.ok(res);
    }

    @PutMapping ("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issues> addUserToIssue (@PathVariable Long issueId,
                                                 @PathVariable Long userId)
            throws Exception {
        Issues issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issues>updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId) throws Exception {
        Issues issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}
