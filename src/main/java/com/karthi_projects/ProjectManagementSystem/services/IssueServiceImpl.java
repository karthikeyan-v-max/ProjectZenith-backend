package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.Issues;
import com.karthi_projects.ProjectManagementSystem.Model.Project;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.repository.IssueRepository;
import com.karthi_projects.ProjectManagementSystem.repository.ProjectRepository;
import com.karthi_projects.ProjectManagementSystem.response.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Override
    public Issues getIssueById(Long issueId) throws Exception {
        Optional<Issues> issue = issueRepository.findById(issueId);

        if(issue.isEmpty()){
            throw new Exception("No issue found with issue id " + issueId);
        }
        return issue.get();
    }

    @Override
    public List<Issues> getIssuesByProjectId(Long projectId) throws Exception {

        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issues createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectbyId(issueRequest.getProjectID());

        Issues issue = new Issues();

        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setPriority(issueRequest.getPriority());
        issue.setProjectID(issueRequest.getProjectID());
        issue.setDueDate(issueRequest.getDueDate());

        issue.setProject(project);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssues(Long issueId, Long userid) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issues addUserToIssue(Long issueId, Long userid) throws Exception {
        User user = userService.findUserById(userid);
        Issues issue = getIssueById(userid);
        issue.setAssignee(user);
        return issueRepository.save(issue);
    }

    @Override
    public Issues updateStatus(Long issueId, String status) throws Exception {
        Issues issue = getIssueById(issueId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
