package com.stg.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Task;

@RepositoryRestResource(exported = true)
public interface TaskDao extends CrudRepository<Task, String> {

    /**
     * Retrieves all tasks for a specific profile
     *
     * @param profileId
     * @return List<Task>
     */
    public List<Task> findByProfileIdEquals(long profileId);
}
