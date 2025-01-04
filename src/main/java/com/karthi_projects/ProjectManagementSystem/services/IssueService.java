package com.karthi_projects.ProjectManagementSystem.services;


import com.karthi_projects.ProjectManagementSystem.Model.Issues;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.response.IssueRequest;


import java.util.List;
import java.util.Optional;


public interface IssueService {
    Issues getIssueById(Long issueId) throws Exception;

    List<Issues> getIssuesByProjectId(Long projectId) throws Exception;

    Issues createIssue(IssueRequest issue , User user) throws Exception;

    void deleteIssues(Long issueId,Long userid) throws Exception;

    Issues addUserToIssue(Long issueId, Long userid) throws Exception;

    Issues updateStatus(Long issueId, String status) throws Exception;
}
