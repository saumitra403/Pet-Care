package com.spapp.universalpetcare.service.user;

import com.spapp.universalpetcare.exception.ResourceNotFoundException;
import com.spapp.universalpetcare.factory.UserFactory;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.UserRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public User register(RegistrationRequest request) {
        return userFactory.createUser(request);
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = findById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSpecialization(request.getSpecialization());
        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
