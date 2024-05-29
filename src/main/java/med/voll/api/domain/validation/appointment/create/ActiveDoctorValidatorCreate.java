package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidatorCreate implements CreateAppointmentValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(CreateAppointmentDto dto) {
        if (dto.doctorId() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(dto.doctorId());
        if (!doctorIsActive) {
            throw new DomainValidationException("Appointment cannot be scheduled with an inactive doctor");
        }
    }
}
