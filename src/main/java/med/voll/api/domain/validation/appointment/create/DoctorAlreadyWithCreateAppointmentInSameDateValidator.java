package med.voll.api.domain.validation.appointment.create;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;
import med.voll.api.domain.validation.interfaces.CreateAppointmentValidator;
import med.voll.api.infra.exceptions.DomainValidationException;
import med.voll.api.infra.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorAlreadyWithCreateAppointmentInSameDateValidator implements CreateAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(CreateAppointmentDto dto) {
        var doctorAlreadyWithAppointmentInSameDate = appointmentRepository.existsByDoctorIdAndDateAndCancelReasonIsNull(dto.doctorId(), dto.date());
        if (doctorAlreadyWithAppointmentInSameDate) {
            throw new DomainValidationException("Doctor already have an appointment in this date");
        }
    }
}
