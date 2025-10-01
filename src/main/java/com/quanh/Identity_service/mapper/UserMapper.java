package com.quanh.Identity_service.mapper;

import com.quanh.Identity_service.dto.request.UserCreationRequest;
import com.quanh.Identity_service.dto.request.UserUpdateRequest;
import com.quanh.Identity_service.dto.response.UserResponse;
import com.quanh.Identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //@Mapping(source = "", target = "")
    //@Mapping(target = "", ignore = true)
    UserResponse toUserResponse(User user);
}
