package com.med.dao;

import com.med.entity.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EventUserRepo extends JpaRepository<EventUser, Long> {
    EventUser findByUserName(String userName);

    EventUser findByEmail(String email);

}
