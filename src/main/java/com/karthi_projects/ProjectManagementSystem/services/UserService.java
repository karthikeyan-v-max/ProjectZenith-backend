package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.User;

public interface UserService{
    User findUserByEmail(String email) throws Exception;
    User findUserProfileByJwt(String jwt) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user , int number);
}
