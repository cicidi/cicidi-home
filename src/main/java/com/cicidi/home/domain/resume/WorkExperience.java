package com.cicidi.home.domain.resume;

import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
public class WorkExperience extends Organization {
    private List<Task> taskList;
    private String summary;
    private String role;


    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
