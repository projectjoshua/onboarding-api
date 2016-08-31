package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.TaskTemplate;

@RepositoryRestResource(exported = true)
public interface TaskTemplateDao extends CrudRepository<TaskTemplate, String> {

}
