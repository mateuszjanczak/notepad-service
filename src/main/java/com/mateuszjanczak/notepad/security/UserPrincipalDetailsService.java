package com.mateuszjanczak.notepad.security;

import com.mateuszjanczak.notepad.users.dao.UserDao;
import com.mateuszjanczak.notepad.users.model.Role;
import com.mateuszjanczak.notepad.users.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserDao userRepository;

    public UserPrincipalDetailsService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);

        return new UserPrincipal(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }

}
