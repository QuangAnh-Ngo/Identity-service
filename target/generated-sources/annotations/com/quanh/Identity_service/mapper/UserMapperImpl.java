package com.quanh.Identity_service.mapper;

import com.quanh.Identity_service.dto.request.UserCreationRequest;
import com.quanh.Identity_service.dto.request.UserUpdateRequest;
import com.quanh.Identity_service.dto.response.UserResponse;
import com.quanh.Identity_service.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-03T21:54:38+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setDob( request.getDob() );
        user.setLastName( request.getLastName() );
        user.setFirstName( request.getFirstName() );

        return user;
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setDob( request.getDob() );
        user.setLastName( request.getLastName() );
        user.setFirstName( request.getFirstName() );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dob( user.getDob() );

        return userResponse.build();
    }
}
