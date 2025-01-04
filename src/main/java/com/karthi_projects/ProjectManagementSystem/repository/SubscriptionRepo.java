package com.karthi_projects.ProjectManagementSystem.repository;

import com.karthi_projects.ProjectManagementSystem.Model.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.Flow;

public interface SubscriptionRepo extends JpaRepository<SubscriptionModel, Long> {

    SubscriptionModel findByUserId(long userID);

}
