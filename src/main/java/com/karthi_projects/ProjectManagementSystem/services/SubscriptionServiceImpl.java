package com.karthi_projects.ProjectManagementSystem.services;

import com.karthi_projects.ProjectManagementSystem.Model.PlanType;
import com.karthi_projects.ProjectManagementSystem.Model.SubscriptionModel;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepo subscriptionRepository;

    @Autowired
    private UserService userService;
    @Override
    public SubscriptionModel createSubscription(User user) {
        SubscriptionModel subscriptionModel = new SubscriptionModel();
        subscriptionModel.setUser(user);

        subscriptionModel.setValid(true);
        subscriptionModel.setSubscriptionStartDate(LocalDate.now());
        subscriptionModel.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscriptionModel.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscriptionModel);
    }

    @Override
    public SubscriptionModel getUsersSubscription(Long userid) throws Exception {
        SubscriptionModel subscriptionModel = subscriptionRepository.findByUserId(userid);
        if(!isValid(subscriptionModel)){
            subscriptionModel.setPlanType((PlanType.FREE));
            subscriptionModel.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscriptionModel.setSubscriptionStartDate(LocalDate.now());

        }
        return subscriptionRepository.save(subscriptionModel);
    }

    @Override
    public SubscriptionModel upgradeSubscription(Long userId, PlanType planType) throws Exception {

        SubscriptionModel subscriptionModel = subscriptionRepository.findByUserId(userId);
        subscriptionModel.setPlanType(planType);
        subscriptionModel.setSubscriptionStartDate(LocalDate.now());
        subscriptionModel.setPlanType(planType);

        if(planType.equals(PlanType.ANNUALLY)){
            subscriptionModel.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }else{
            subscriptionModel.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscriptionModel);
    }

    @Override
    public boolean isValid(SubscriptionModel subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)){
            return true;
        }

        LocalDate endDate = subscription.getGetSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();
        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
