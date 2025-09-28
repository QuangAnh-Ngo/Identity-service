package com.quanh.Identity_service.service;

import com.quanh.Identity_service.dto.request.RoleRequest;
import com.quanh.Identity_service.dto.response.RoleResponse;
import com.quanh.Identity_service.entity.Role;
import com.quanh.Identity_service.mapper.RoleMapper;
import com.quanh.Identity_service.repository.PermissionRepository;
import com.quanh.Identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void deleteRole(String roleName){
        roleRepository.deleteById(roleName);
    }
}
