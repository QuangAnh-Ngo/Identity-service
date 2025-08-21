package com.quanh.Identity_service.controller;

import com.quanh.Identity_service.dto.request.ApiResponse;
import com.quanh.Identity_service.dto.request.UserCreationRequest;
import com.quanh.Identity_service.dto.request.UserUpdateRequest;
import com.quanh.Identity_service.dto.response.UserResponse;
import com.quanh.Identity_service.entity.User;
import com.quanh.Identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    /*
    @PostMapping
    User createUser(@RequestBody @Valid UserCreationRequest request){
        return userService.createUser(request);
    }
    */
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    //"userId" là khai báo tường minh. Nếu không để anno thì sẽ tự động truyền userId ở trên vào Path
    //Cach k su dung se phu thuoc vao ten biến: nếu @PathVariable String id => lỗi
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }
}
