package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;

public interface UserFactory {
    public User createUser(RegistrationRequest registrationRequest);
}
