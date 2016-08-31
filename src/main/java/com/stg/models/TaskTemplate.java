package com.stg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_templates")
public class TaskTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "task")
    private String task;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory category;

    @Column(name = "day_count")
    private int dayCount;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
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

    @Override
    public String toString() {
	return "TaskTemplate [id=" + id + ", task=" + task + ", category=" + category + ", dayCount=" + dayCount + "]";
    }

}
