package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.Invitation;

public interface InvitationService {

    void sendInvitation(String email,Long projectId) throws Exception;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserMail(String userEmail);

    void deleteToken(String token);
}
