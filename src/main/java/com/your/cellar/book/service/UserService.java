package com.your.cellar.book.service;

import com.your.cellar.book.entity.User;
import com.your.cellar.book.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

//    private BCryptPasswordEncoder passwordEncoder;

    /*@Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> fetchAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().collect(Collectors.toSet());
    }

/*    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLastLoggedIn(LocalDateTime.now());

            user = userRepository.save(user);
            return user;
        }
        return null;
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Load roles and map them to authorities
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, // Account not expired
                true, // Credentials not expired
                true, // Account not locked
                authorities);
    }*/


}

