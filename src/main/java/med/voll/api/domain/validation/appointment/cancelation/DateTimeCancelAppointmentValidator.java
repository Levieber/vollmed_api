package med.voll.api.domain.validation.appointment.cancelation;

import med.voll.api.application.dtos.appointment.CancelAppointmentDto;
import med.voll.api.domain.validation.interfaces.CancelAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class DateTimeCancelAppointmentValidator implements CancelAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(Long id, CancelAppointmentDto dto) {
        var appointment = appointmentRepository.getReferenceById(id);
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, appointment.getDate()).toHours();

        if (diferencaEmHoras < 24) {
            throw new DomainValidationException("Appointment can only be canceled at least 24 hours in advance");
        }
    }
}
