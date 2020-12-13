package com.med.dao;

import com.med.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByRoleName(String roleName);

}
