package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.model.Admin;
import com.spapp.universalpetcare.model.Patient;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.AdminRepository;
import com.spapp.universalpetcare.repository.PatientRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory {
    private final PatientRepository patientRepository;
    private final UserAttributeMapper userAttributeMapper;
    public Patient createPatient(RegistrationRequest registrationRequest) {
        Patient patient = new Patient();
        userAttributeMapper.setCommonAttributes(registrationRequest, patient);
        return patientRepository.save(patient);
    }
}
