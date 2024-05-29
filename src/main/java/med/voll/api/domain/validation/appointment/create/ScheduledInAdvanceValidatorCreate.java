package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduledInAdvanceValidatorCreate implements CreateAppointmentValidator {
    public void validate(CreateAppointmentDto dto) {
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dto.date()).toMinutes();
        if (differenceInMinutes < 30) {
            throw new DomainValidationException("Appointment must be scheduled 30 minutes in advance");
        }
    }
}
