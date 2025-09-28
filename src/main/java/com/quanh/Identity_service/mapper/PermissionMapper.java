package com.quanh.Identity_service.mapper;

import com.quanh.Identity_service.dto.request.PermissionRequest;
import com.quanh.Identity_service.dto.response.PermissionResponse;
import com.quanh.Identity_service.entity.Permission;
import com.quanh.Identity_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
