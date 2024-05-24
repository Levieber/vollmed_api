package med.voll.api.application.dtos.doctor;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.DoctorSpeciality;

public record ListDoctorsDto(Long id, String name, String email, String crm, DoctorSpeciality speciality) {
    public ListDoctorsDto(Doctor entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCrm(), entity.getSpeciality());
    }
}