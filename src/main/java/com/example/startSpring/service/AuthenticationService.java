package com.example.startSpring.service;

import com.example.startSpring.dto.request.AuthenticationRequest;
import com.example.startSpring.dto.response.AuthenticationResponse;
import com.example.startSpring.exception.AppException;
import com.example.startSpring.exception.ErrorCode;
import com.example.startSpring.repository.UserRepository;
import com.nimbusds.jose.JWSObject;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticate =  passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticate)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    String generateToken(String username) {

        JWSObject jwsObject = new JWSObject();

    }
}
