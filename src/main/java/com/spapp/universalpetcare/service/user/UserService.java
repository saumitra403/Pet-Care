package com.spapp.universalpetcare.service.user;

import com.spapp.universalpetcare.factory.UserFactory;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.UserRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    public User add(RegistrationRequest request) {
        return userFactory.createUser(request);
    }
}
