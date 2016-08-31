package com.stg.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Profile;
import com.stg.models.User;

@RepositoryRestResource(exported = false)
public interface ProfileDao extends CrudRepository<Profile, String> {

    /**
     * Finds a currently open profile for a user
     *
     * @param userId
     * @return List<Profile>
     */
    public List<Profile> findByEndDateIsNullAndUserIdEquals(long userId);

    /**
     * Finds all users where the starting date is a specific date
     *
     * @param startDate
     * @return List<User>
     */
    public List<User> findUsersByStartingDate(Date startDate);

    /**
     * Retrieves the number of profiles for a specific user
     *
     * @param userId
     * @return Long
     */
    public Long countByUserIdEquals(long userId);
}
