package com.stg.helpers;

import java.util.ArrayList;
import java.util.List;

import com.stg.models.Profile;
import com.stg.models.Task;
import com.stg.models.TaskTemplate;

public class TaskTemplateConverter {

    public static List<Task> convertList(List<TaskTemplate> taskTemplateList,
	    Profile userProfile) {
	List<Task> taskList = new ArrayList<>();

	for (TaskTemplate temp : taskTemplateList) {
	    Task task = new Task();
	    task.setCategory(temp.getCategory());
	    task.setTask(temp.getTask());
	    task.setProfile(userProfile);

	    taskList.add(task);
	}

	return taskList;
    }
}
