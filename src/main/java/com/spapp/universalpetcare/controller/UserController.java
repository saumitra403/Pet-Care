package com.spapp.universalpetcare.controller;

import com.spapp.universalpetcare.dto.EntityConverter;
import com.spapp.universalpetcare.dto.UserDto;
import com.spapp.universalpetcare.exception.UserAlreadyExistsException;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.response.ApiResponse;
import com.spapp.universalpetcare.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;
    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody RegistrationRequest request) {
        try {
            User theUser = userService.add(request);

            UserDto registeredUser = entityConverter.mapEntityToDto(theUser, UserDto.class);

            return ResponseEntity.ok(new ApiResponse("User registered successfully", registeredUser));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }
}
