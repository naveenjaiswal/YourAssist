package com.example.asifsheikh.yourassist.model;

import com.example.asifsheikh.yourassist.application.YourAssistApp;

import java.util.Date;

/**
 * Created by asifsheikh on 7/9/15.
 */
public class Task {

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    private int task_id;
    private String task_name;
    private String task_description;
    private String priority;
    private Date start_date;
    private Date due_date;

    public int getTotal_number_of_subtask() {
        return total_number_of_subtask;
    }

    public void setTotal_number_of_subtask(int total_number_of_subtask) {
        this.total_number_of_subtask = total_number_of_subtask;
    }

    public int getComplete_subtask() {
        return complete_subtask;
    }

    public void setComplete_subtask(int complete_subtask) {
        this.complete_subtask = complete_subtask;
    }

    private int total_number_of_subtask;
    private int complete_subtask;

    public Task(){


    }

    public Task( String mtaskname,String mtask_desp,String mpriority,Date mstartdate, Date mduedate){
        this.task_id = ++YourAssistApp.getAppInstance().numberofTasks;
        this.task_name = mtaskname;
        this.task_description = mtask_desp;
        this.priority = mpriority;
        this.due_date = mduedate;
        this.start_date = mstartdate;
    }


    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
}
