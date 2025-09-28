package com.quanh.Identity_service.service;

import com.quanh.Identity_service.dto.request.PermissionRequest;
import com.quanh.Identity_service.dto.response.PermissionResponse;
import com.quanh.Identity_service.entity.Permission;
import com.quanh.Identity_service.mapper.PermissionMapper;
import com.quanh.Identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().
                map(permissionMapper::toPermissionResponse).
                toList();

    }

    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
