package com.spapp.universalpetcare.service.user;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.request.UserUpdateRequest;

public interface IUserService {
    User register(RegistrationRequest request);

    User updateUser(Long userId, UserUpdateRequest request);
}
