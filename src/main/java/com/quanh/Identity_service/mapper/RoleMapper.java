package com.quanh.Identity_service.mapper;

import com.quanh.Identity_service.dto.request.RoleRequest;
import com.quanh.Identity_service.dto.response.RoleResponse;
import com.quanh.Identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true) //Bỏ qua trường permissions khi ánh xạ từ RoleRequest sang Role
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
