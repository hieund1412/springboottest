package com.example.startSpring.controller;

import com.example.startSpring.dto.request.ApiResponseConfig;
import com.example.startSpring.dto.request.UserCreationRequest;
import com.example.startSpring.dto.request.UserDto;
import com.example.startSpring.dto.request.UserInsertDto;
import com.example.startSpring.entity.User;
import com.example.startSpring.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@Api(value = "User Management System", description = "Operations pertaining to user management")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponseConfig<User> createUser(@RequestBody @Valid UserInsertDto request){
        ApiResponseConfig<User> apiResponseConfig = new ApiResponseConfig<>();
        apiResponseConfig.setResult(userService.createRequest(request));
        return apiResponseConfig;
    }

    @Tag(name = "get", description = "GET methods of User APIs")
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @Tag(name = "get", description = "GET methods of User APIs")
    @GetMapping("/{userId}")
    User getUser(@Parameter(
            description = "ID of user to be retrieved",
            required = true) @PathVariable("userId") long userId){
        return userService.getUser(userId);
    }

    @Operation(summary = "Update an user",
            description = "Update an existing user. The response is updated User object with first name, last name, password, dob.")
    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") long userId, @RequestBody UserDto request){
        return userService.updateUser(userId, request);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") long userId){
        userService.deleteUser(userId);
        return "User has been deleted!";
    }
}
