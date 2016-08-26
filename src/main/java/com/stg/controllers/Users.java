package com.stg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stg.daos.UserDao;
import com.stg.helpers.MailHelper;
import com.stg.models.User;

@BasePathAwareController
@RequestMapping(value = "/users", produces = "application/json")
public class Users implements ResourceProcessor<RepositorySearchesResource>,
	ResourceAssembler<User, Resource<User>> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private MailHelper mailHelper;

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ResponseEntity<Resource<User>> getAllUsers(@RequestBody User user)
	    throws Exception {
	User existingUser = userDao.findByEmail(user.getEmail());

	if (existingUser != null) {
	    user = existingUser;
	    // TODO: Send Welcome Back Email
	    try {
		mailHelper.sendMail();
	    } catch (Exception e) {
		e.printStackTrace();
		throw e;
	    }
	} else {
	    // TODO: Save user
	    System.out.println(user);
	    userDao.save(user);
	    System.out.println(existingUser);

	    // TODO: Send Welcome Email
	}

	try {
	    Resource<User> resource = toResource(user);
	    return new ResponseEntity<Resource<User>>(resource, HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<Resource<User>>(HttpStatus.OK);
	}
    }

    @Override
    public Resource<User> toResource(User user) {
	Resource<User> resource = new Resource<User>(user);

	return resource;
    }

    @Override
    public RepositorySearchesResource process(
	    RepositorySearchesResource resource) {
	LinkBuilder link = entityLinks.linkFor(User.class, "add-user");
	resource.add(new Link(link.toString() + "/add-user", "add-user"));

	return resource;
    }

}
