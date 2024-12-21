package com.spapp.universalpetcare.service.user;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }
}
