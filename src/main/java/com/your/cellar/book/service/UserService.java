package com.your.cellar.book.service;

import com.your.cellar.book.dto.RoleDto;
import com.your.cellar.book.dto.converter.RoleConverter;
import com.your.cellar.book.dto.converter.UserConverter;
import com.your.cellar.book.dto.request.UserRequestModel;
import com.your.cellar.book.dto.response.UserResponseModel;
import com.your.cellar.book.entity.Role;
import com.your.cellar.book.entity.User;
import com.your.cellar.book.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;
    private RoleConverter roleConverter;


    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder, RoleConverter roleConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.roleConverter = roleConverter;
    }

    public Set<User> fetchAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().collect(Collectors.toSet());
    }


    public UserResponseModel createUser(UserRequestModel userRequestModel) throws RoleNotFoundException {
        log.info("Create user request model: {}", userRequestModel);

        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : userRequestModel.getRoles()) {
            roles.add(roleConverter.roleDtoToRole(roleDto));
        }

        if (userRepository.findByUsername(userRequestModel.getUsername()) == null) {
            User user = userConverter.userDtoToUser(userRequestModel);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLastLoggedIn(LocalDateTime.now());

            user = userRepository.save(user);
            log.info("Saved user: {}", user);
            return userConverter.userToUserDto(user);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        return authorities;
    }


}

