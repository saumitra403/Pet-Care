package com.spapp.universalpetcare.controller;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    @PostMapping
    public User add(@RequestBody RegistrationRequest request) {
        return userService.add(request);
    }
}
