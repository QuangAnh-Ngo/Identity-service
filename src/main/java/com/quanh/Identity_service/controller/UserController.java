package com.quanh.Identity_service.controller;

import com.quanh.Identity_service.dto.response.ApiResponse;
import com.quanh.Identity_service.dto.request.UserCreationRequest;
import com.quanh.Identity_service.dto.request.UserUpdateRequest;
import com.quanh.Identity_service.dto.response.UserResponse;
import com.quanh.Identity_service.entity.User;
import com.quanh.Identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

//    /*
//    @PostMapping
//    User createUser(@RequestBody @Valid UserCreationRequest request){
//        return userService.createUser(request);
//    }
//    */
//    @PostMapping
//    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
//        ApiResponse<User> apiResponse = new ApiResponse<>();
//
//        apiResponse.setResult(userService.createUser(request));
//
//        return apiResponse;
//    }
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){

        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        //cho phép truy cập thông tin chi tiết về người dùng đã được xác thực bên trong phương thức
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    /*
    @GetMapping("/{userId}")
    //"userId" là khai báo tường minh. Nếu không để anno thì sẽ tự động truyền userId ở trên vào Path
    //Cach k su dung se phu thuoc vao ten biến: nếu @PathVariable String id => lỗi
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }
    */

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){

        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){

        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){

        return ApiResponse.<String>builder()
                .result(userService.deleteUser(userId))
                .build();
    }
}
