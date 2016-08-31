package com.stg.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "task")
    private String task;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @Column(name = "day_count")
    private int dayCount;

    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getTask() {
	return task;
    }

    public void setTask(String task) {
	this.task = task;
    }

    public TaskCategory getCategory() {
	return category;
    }

    public void setCategory(TaskCategory category) {
	this.category = category;
    }

    public int getDayCount() {
	return dayCount;
    }

    public void setDayCount(int dayCount) {
	this.dayCount = dayCount;
    }

    public Date getCompletionDate() {
	return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
	this.completionDate = completionDate;
    }

    @Override
    public String toString() {
	return "Task [id=" + id + ", user=" + user + ", task=" + task + ", category=" + category + ", dayCount=" + dayCount + ", completionDate=" + completionDate + "]";
    }

}
