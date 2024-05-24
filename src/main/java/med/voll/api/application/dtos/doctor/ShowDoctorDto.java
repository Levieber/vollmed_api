package med.voll.api.application.dtos.doctor;

import med.voll.api.application.dtos.address.ReadAddressDto;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.DoctorSpeciality;

public record ShowDoctorDto(
        Long id,
        String name,
        String email,
        String phone,
        String crm,
        DoctorSpeciality speciality,
        ReadAddressDto address) {
    public ShowDoctorDto(Doctor entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCrm(),
                entity.getSpeciality(),
                new ReadAddressDto(entity.getAddress())
        );
    }
}