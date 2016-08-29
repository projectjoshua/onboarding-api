package com.stg.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Profile;
import com.stg.models.User;

@RepositoryRestResource(exported = false)
public interface ProfileDao extends CrudRepository<Profile, String> {

    public List<Profile> findByEndDateIsNullAndUserIdEquals(long userId);

    public List<User> findUsersByStartingDate(Date startDate);

    public Long countByUserIdEquals(long userId);
}
