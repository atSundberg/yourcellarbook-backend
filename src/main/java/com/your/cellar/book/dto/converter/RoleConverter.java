package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.RoleDto;
import com.your.cellar.book.entity.Role;
import com.your.cellar.book.repository.RoleRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.RoleNotFoundException;

@Slf4j
@Component
@NoArgsConstructor
public class RoleConverter {

    private ModelMapper modelMapper;
    private RoleRepository roleRepository;

    @Autowired
    public RoleConverter(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public Role roleDtoToRole(RoleDto roleDto) throws RoleNotFoundException {
        Role role = roleRepository.findRoleByRoleName(roleDto.getRoleName());
        log.info("RoleConverter role: {}", role);
        if (role == null) {
            throw new RoleNotFoundException("Could not find a role by name: " + roleDto.getRoleName());
        }
        return role;
    }

    public RoleDto roleToRoleDto(Role role) {
        return this.modelMapper.map(role, RoleDto.class);
    }
}
