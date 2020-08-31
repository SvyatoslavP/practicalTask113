package ru.panifidkin.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.panifidkin.springsecurityapp.dao.UserDao;
import ru.panifidkin.springsecurityapp.model.Role;
import ru.panifidkin.springsecurityapp.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }
}