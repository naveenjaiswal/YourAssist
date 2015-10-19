package com.example.asifsheikh.yourassist.model;

import java.util.Date;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTask {

    private int task_id;
    private int subtask_id;
    private String subtask_name;

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(int subtask_id) {
        this.subtask_id = subtask_id;
    }

    public String getSubtask_name() {
        return subtask_name;
    }

    public void setSubtask_name(String subtask_name) {
        this.subtask_name = subtask_name;
    }

    public String getSubtask_description() {
        return subtask_description;
    }

    public void setSubtask_description(String subtask_description) {
        this.subtask_description = subtask_description;
    }

    private String subtask_description;




}
