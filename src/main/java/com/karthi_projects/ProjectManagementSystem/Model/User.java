package com.karthi_projects.ProjectManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }

    public List<Issues> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issues> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }

    private String fullname;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private int projectSize;


//    This annotation comes from the Jackson library and is used to ignore this field during JSON serialization and deserialization.
    @JsonIgnore
    //The cascade property defines how operations performed on the parent entity (User) are propagated to the child entities (Issues).
    //
    //CascadeType.ALL:
    //All operations (e.g., PERSIST, MERGE, REMOVE, REFRESH, DETACH) on the User entity will also apply to the assignedIssues.
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
    private List<Issues> assignedIssues = new ArrayList<>();
}
