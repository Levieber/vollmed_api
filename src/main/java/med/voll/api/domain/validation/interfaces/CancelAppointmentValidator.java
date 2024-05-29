package med.voll.api.domain.validation.interfaces;

import med.voll.api.application.dtos.appointment.CancelAppointmentDto;

public interface CancelAppointmentValidator {
    void validate(Long id, CancelAppointmentDto dto);
}
