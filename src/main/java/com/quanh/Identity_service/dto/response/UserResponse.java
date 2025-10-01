package com.quanh.Identity_service.dto.response;

import com.quanh.Identity_service.enums.Role;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    //String password;//Trong thực tế không trả về password, chỉ dùng để verify thôi
    String firstName;
    String lastName;
    LocalDate dob;

    @ManyToMany
    Set<RoleResponse> roles;
}
