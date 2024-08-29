package com.example.startSpring.controller;

import com.example.startSpring.dto.request.ApiResponseConfig;
import com.example.startSpring.dto.request.AuthenticationRequest;
import com.example.startSpring.dto.response.AuthenticationResponse;
import com.example.startSpring.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("log-in")
    ApiResponseConfig<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.authenticate(request);
        return ApiResponseConfig.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticate(result)
                        .build())
                .build();
    }
}
