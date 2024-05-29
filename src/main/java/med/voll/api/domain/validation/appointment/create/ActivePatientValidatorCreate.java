package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidatorCreate implements CreateAppointmentValidator {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(CreateAppointmentDto dto) {
        var patientIsActive = patientRepository.findActiveById(dto.patientId());
        if (!patientIsActive) {
            throw new DomainValidationException("Appointment cannot be scheduled with an inactive patient");
        }
    }
}

