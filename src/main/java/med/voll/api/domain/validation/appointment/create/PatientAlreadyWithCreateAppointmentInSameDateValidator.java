package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientAlreadyWithCreateAppointmentInSameDateValidator implements CreateAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(CreateAppointmentDto dto) {
        var firstHour = dto.date().withHour(7);
        var lastHour = dto.date().withHour(18);
        var patientAlreadyWithAppointmentInSameDate = appointmentRepository.existsByPatientIdAndDateBetweenAndCancelReasonIsNull(dto.patientId(), firstHour, lastHour);
        if (patientAlreadyWithAppointmentInSameDate) {
            throw new DomainValidationException("Patient already have an appointment in this day");
        }
    }
}
