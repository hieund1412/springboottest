package com.example.startSpring.service;

import com.example.startSpring.dto.request.UserCreationRequest;
import com.example.startSpring.dto.request.UserDto;
import com.example.startSpring.dto.request.UserInsertDto;
import com.example.startSpring.entity.User;
import com.example.startSpring.exception.AppException;
import com.example.startSpring.exception.ErrorCode;
import com.example.startSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserInsertDto request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(long id){
        return userRepository.findById(Long.toString(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(long id, UserDto request){
        User user = getUser(id);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(Long.toString(id));
    }
}
