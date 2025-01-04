package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.PlanType;
import com.karthi_projects.ProjectManagementSystem.Model.SubscriptionModel;
import com.karthi_projects.ProjectManagementSystem.Model.User;

public interface SubscriptionService {
    SubscriptionModel createSubscription(User user);

    SubscriptionModel getUsersSubscription(Long userid) throws Exception;

    SubscriptionModel upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(SubscriptionModel subscription);

}
