package com.quanh.Identity_service.controller;

import com.quanh.Identity_service.dto.request.RoleRequest;
import com.quanh.Identity_service.dto.response.ApiResponse;
import com.quanh.Identity_service.dto.response.RoleResponse;
import com.quanh.Identity_service.service.RoleService;
import com.quanh.Identity_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse roleResponse = roleService.createRole(roleRequest);
        return ApiResponse.<RoleResponse>builder()
                .result(roleResponse)
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        List<RoleResponse> roleResponses = roleService.getAll();
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleResponses)
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> deleteRole(@PathVariable String role) {
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder()
                .build();
    }
}
