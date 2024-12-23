package com.spapp.universalpetcare.factory;

import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.model.Veterinarian;
import com.spapp.universalpetcare.repository.VeterinarianRepository;
import com.spapp.universalpetcare.request.RegistrationRequest;
import com.spapp.universalpetcare.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeterinarianFactory {
    private final VeterinarianRepository veterinarianRepository;
    private final UserAttributeMapper userAttributeMapper;

    public Veterinarian createVeterinarian(RegistrationRequest request) {
        Veterinarian veterinarian = new Veterinarian();
        userAttributeMapper.setCommonAttributes(request,veterinarian);
        veterinarian.setSpecialization(request.getSpecialization());
        return veterinarianRepository.save(veterinarian);
    }
}
