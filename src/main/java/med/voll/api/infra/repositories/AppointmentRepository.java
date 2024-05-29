package med.voll.api.infra.repositories;

import med.voll.api.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDateAndCancelReasonIsNull(Long doctorId, LocalDateTime date);
    boolean existsByPatientIdAndDateBetweenAndCancelReasonIsNull(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);
}
