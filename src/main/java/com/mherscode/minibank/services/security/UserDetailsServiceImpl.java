package com.mherscode.minibank.services.security;

import com.mherscode.minibank.model.User;
import com.mherscode.minibank.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        LOG.info("User: {} ", user);

        UserBuilder builder;

        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
            builder.password(user.getPassword());
            builder.disabled(!user.getEnabled());
            // get all user roles and add to the user builder roles
            List<String> mRoles = new ArrayList<>();
            user.getRoles().forEach(role -> mRoles.add(role.getRoleName()));
            builder.roles(mRoles.toArray(new String[user.getRoles().size()]));

            builder.authorities(getGrantedAuthorities(user));
        } else {
            LOG.info("User not found!");
            throw new UsernameNotFoundException("Username " + username + " not found in the application.");
        }

        return builder.build();
    }

    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        LOG.info("authorities: {}", authorities);

        return authorities;
    }
}
