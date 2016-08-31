package com.stg.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Task;

@RepositoryRestResource(exported = true)
public interface TaskDao extends CrudRepository<Task, String> {

    /**
     * Retrieves all tasks for a specific user
     *
     * @param userId
     * @return List<Task>
     */
    public List<Task> findByUserIdEquals(long userId);
}
