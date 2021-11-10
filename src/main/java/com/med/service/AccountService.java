package com.med.service;

import com.med.entity.UserRole;
import com.med.entity.EventUser;

public interface AccountService {
    EventUser saveUser(String userName, String email, String passWord );

    UserRole saveRole(UserRole role);

    EventUser loadUserByUserName(String userName);

    void addRoleToUser(String userName, String roleName);
}
