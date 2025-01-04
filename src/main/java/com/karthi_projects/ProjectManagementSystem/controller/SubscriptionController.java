package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.Model.PlanType;
import com.karthi_projects.ProjectManagementSystem.Model.SubscriptionModel;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.services.SubscriptionService;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<SubscriptionModel> getUserSubscription(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserProfileByJwt(jwt);

        SubscriptionModel subscription = subscriptionService.getUsersSubscription(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<SubscriptionModel> upgradeSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType plantype) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        SubscriptionModel subscription = subscriptionService.upgradeSubscription(user.getId() , plantype);
       return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
