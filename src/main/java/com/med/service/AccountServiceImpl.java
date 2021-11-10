package com.med.service;

import com.med.dao.UserRoleRepo;
import com.med.dao.EventUserRepo;
import com.med.entity.UserRole;
import com.med.entity.EventUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountServiceImpl implements AccountService {

    private final EventUserRepo eventUserRepo;
    private final UserRoleRepo userRoleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AccountServiceImpl(EventUserRepo eventUserRepo, UserRoleRepo userRoleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.eventUserRepo = eventUserRepo;
        this.userRoleRepo = userRoleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public EventUser saveUser(String userName, String email, String passWord) {
        EventUser user1 = eventUserRepo.findByUserName(userName);
        EventUser user2 = eventUserRepo.findByEmail(email);
        if (user1 != null)
            throw new RuntimeException("User Already exists");
        if (user2 != null)
            throw new RuntimeException("email already used");
        EventUser eventUser = new EventUser();
        eventUser.setUserName(userName);
        eventUser.setPassWord(bCryptPasswordEncoder.encode(passWord));
        eventUser.setActivated(true);
        eventUser.setEmail(email);
        eventUserRepo.save(eventUser);


        UserRole role = userRoleRepo.findByRoleName("USER");
        if (role == null) {
            role = new UserRole("USER");
        }
        eventUser.addRole(role);

        return eventUser;
    }

    @Override
    public UserRole saveRole(UserRole role) {
        return userRoleRepo.save(role);
    }

    @Override
    public EventUser loadUserByUserName(String userName) {
        return eventUserRepo.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        EventUser user = eventUserRepo.findByUserName(userName);
        UserRole role = userRoleRepo.findByRoleName(roleName);

        if (role == null)
            role = new UserRole(roleName);

        user.getRoles().add(role);
    }


}
