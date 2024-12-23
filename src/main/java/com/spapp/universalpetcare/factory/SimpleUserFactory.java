package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.exception.UserAlreadyExistsException;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.UserRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleUserFactory implements UserFactory {
    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final PatientFactory patientFactory;
    private final AdminFactory adminFactory;

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("Oops! " + registrationRequest.getEmail() + " already exists");
        }
        switch (registrationRequest.getUserType()) {
            case "VET" -> {
                return veterinarianFactory.createVeterinarian(registrationRequest);
            }
            case "PATIENT" -> {
                return patientFactory.createPatient(registrationRequest);
            }
            case "ADMIN" -> {
                return adminFactory.createAdmin(registrationRequest);
            }
            default -> {
                return null;
            }
        }
    }
}
