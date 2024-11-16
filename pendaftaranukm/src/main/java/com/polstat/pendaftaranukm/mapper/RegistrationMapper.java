package com.polstat.pendaftaranukm.mapper;

import com.polstat.pendaftaranukm.dto.RegistrationDto;
import com.polstat.pendaftaranukm.entity.Registration;

public class RegistrationMapper {
    public static Registration mapToRegistration(RegistrationDto registrationDto) {
        return Registration.builder()
            .id(registrationDto.getId())
            .user(registrationDto.getUser())
            .ukm(registrationDto.getUkm())
            .registrationDate(registrationDto.getRegistrationDate())
            .status(registrationDto.getStatus())
            .build();
    }
    public static RegistrationDto mapToRegistrationDto(Registration registration) {
        return RegistrationDto.builder()
            .id(registration.getId())
            .user(registration.getUser())
            .ukm(registration.getUkm())
            .registrationDate(registration.getRegistrationDate())
            .status(registration.getStatus())
            .build();
    }
}
