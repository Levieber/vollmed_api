package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHoursValidatorCreate implements CreateAppointmentValidator {
    public void validate(CreateAppointmentDto dto) {
        var sunday = dto.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpen = dto.date().getHour() < 7;
        var afterClinicClose = dto.date().getHour() > 18;

        if (sunday || beforeClinicOpen || afterClinicClose) {
            throw new DomainValidationException("Appointment outside clinic opening hours");
        }
    }
}
