package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.Model.Comment;
import com.karthi_projects.ProjectManagementSystem.Model.CommentService;
import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.request.CreateCommentRequest;
import com.karthi_projects.ProjectManagementSystem.services.ProjectService;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Comment createComment = commentService.createComment(req.getIssueId() , user.getId(),req.getContent());
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,
                                                @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId , user.getId());
        return new ResponseEntity<>("Comment has been Deleted", HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId){
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
