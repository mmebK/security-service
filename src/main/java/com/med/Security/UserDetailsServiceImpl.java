package com.med.Security;

import com.med.entity.EventUser;
import com.med.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        EventUser eventUser = accountService.loadUserByUserName(userName);

        if (eventUser == null)
            throw new UsernameNotFoundException("invalid user");
        List<GrantedAuthority> authorities = new ArrayList<>();
        eventUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(eventUser.getUserName(), eventUser.getPassWord(), authorities);
    }
}
