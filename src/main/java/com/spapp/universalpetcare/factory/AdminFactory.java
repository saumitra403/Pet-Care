package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.model.Admin;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.AdminRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory {
    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;

    public Admin createAdmin(RegistrationRequest registrationRequest) {
        Admin admin = new Admin();
        userAttributeMapper.setCommonAttributes(registrationRequest, admin);
        return adminRepository.save(admin);
    }
}
