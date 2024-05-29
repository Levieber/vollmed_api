package med.voll.api.domain.validation.interfaces;

import med.voll.api.application.dtos.appointment.CreateAppointmentDto;

public interface CreateAppointmentValidator {
    void validate(CreateAppointmentDto dto);
}
