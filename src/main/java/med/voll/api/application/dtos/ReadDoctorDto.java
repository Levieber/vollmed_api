package med.voll.api.application.dtos;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.DoctorSpeciality;

public record ReadDoctorDto(Long id, String name, String email, String crm, DoctorSpeciality speciality) {
    public ReadDoctorDto(Doctor entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCrm(), entity.getSpeciality());
    }
}