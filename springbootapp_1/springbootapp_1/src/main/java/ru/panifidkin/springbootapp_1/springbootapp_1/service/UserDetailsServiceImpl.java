package ru.panifidkin.springbootapp_1.springbootapp_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.UserRole;
import ru.panifidkin.springbootapp_1.springbootapp_1.repositoty.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        for (UserRole role : user.getRoles()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthoritySet);

    }
}
