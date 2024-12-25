package com.spapp.universalpetcare.service.user;

import com.spapp.universalpetcare.dto.UserDto;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.request.UserUpdateRequest;

import java.util.List;

public interface IUserService {
    User register(RegistrationRequest request);

    User updateUser(Long userId, UserUpdateRequest request);

    User findById(Long userId);

    void delete(Long userId);

    List<UserDto> getAllUsers();
}
