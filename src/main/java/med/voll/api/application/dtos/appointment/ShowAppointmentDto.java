package med.voll.api.application.dtos.appointment;

import med.voll.api.domain.entities.Appointment;

import java.time.LocalDateTime;

public record ShowAppointmentDto(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public ShowAppointmentDto(Appointment entity) {
        this(entity.getId(), entity.getDoctor().getId(), entity.getPatient().getId(), entity.getDate());
    }
}
