package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.request.RegistrationRequest;

public class SimpleUserFactory implements UserFactory {
    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        return null;
    }
}
